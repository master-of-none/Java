#create simulator object
set ns [new Simulator]

set nf [open node4.nam w]
$ns namtrace-all $nf

set f [open node4.tr w]
$ns trace-all $f

#finish procedure
proc finish {} {
	global ns nf f
	$ns flush-trace
	exec nam node4.nam &
	close $nf
	close $f
	exit 0
}

#create nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]

#create duplex links
$ns duplex-link $n0 $n2 1mb 10ms DropTail
$ns duplex-link $n1 $n2 1mb 10ms DropTail
$ns duplex-link $n2 $n3 1mb 10ms DropTail
$ns duplex-link-op $n0 $n2 orient left-down
$ns duplex-link-op $n2 $n1 orient left-down
$ns duplex-link-op $n2 $n3 orient right-down


#create udp
set udp0 [new Agent/UDP]
$ns attach-agent $n1 $udp0

#create cbr application
set cbr0 [new Application/Traffic/CBR]
$cbr0 set packetSize_ 500
$cbr0 set interval_ 0.005
$cbr0 attach-agent $udp0

#create null agent
set null0 [new Agent/Null]
$ns attach-agent $n3 $null0

#create tcp
set tcp0 [new Agent/TCP]
$ns attach-agent $n0 $tcp0

#create ftp app
set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0

#create tcpsink
set tcpSink0 [new Agent/TCPSink]
$ns attach-agent $n3 $tcpSink0

#start / stop simulation
$ns at 0.5 "$cbr0 start"
$ns at 1.0 "$ftp0 start"
$ns at 4.5 "$cbr0 stop"
$ns at 5.0 "$ftp0 stop"

$ns connect $udp0 $null0
$ns connect $tcp0 $tcpSink0

$ns at 5.5 "finish"
$ns run
