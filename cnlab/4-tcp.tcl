set ns [new Simulator]

set nf [open out.nam w]
$ns namtrace-all $nf

proc finish {} {
	global ns nf
	$ns flush-trace
	close $nf
	exec nam out.nam &
	exit 0
}

set n0 [$ns node]
set n1 [$ns node]

$ns duplex-link $n0 $n1 1Mb 10ms DropTail

set udp0 [new Agent/UDP]
$ns attach-agent $n0 $udp0

set cbr0 [new Application/Traffic/CBR]
$cbr0 set packetSize_ 1024
$cbr0 set interval_ 0.005
$cbr0 attach-agent $udp0

set null0 [new Agent/Null]
$ns attach-agent $n1 $null0

$ns connect $udp0 $null0

$ns at 0.5 "$cbr0 start"
$ns at 4.5 "$cbr0 stop"

set tcp0 [new Agent/TCP]
$ns attach-agent $n0 $tcp0

set sink [new Agent/TCPSink]
$ns attach-agent $n1 $sink

$ns connect $tcp0 $sink

set ftp [new Application/FTP]
$ftp attach-agent $tcp0

#$ns at 0.5 "$ftp start"
#$ns at 3.0 "$ftp stop"

$ns run
