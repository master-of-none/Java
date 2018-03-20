#create event scheduler
set ns [new Simulator]

#set tf [open three-nodes.tr w]
#$ns trace-all $tf

#initiate tracing 
set nf [open out.nam w]
$ns namtrace-all $nf

#define finish procedure
proc finish {} {
	global ns nf
	$ns flush-trace
	close $nf
	exec nam out.nam &
	exit 0
}

#create 3 nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]

#create duplex links
$ns duplex-link $n0 $n1 1Mb 10ms DropTail
$ns duplex-link $n1 $n2 1Mb 10ms DropTail
$ns duplex-link-op $n0 $n1 orient right-down
$ns duplex-link-op $n1 $n2 orient righ-down

#create udp agent
set udp0 [new Agent/UDP]
$ns attach-agent $n0 $udp0
#set udp1 [new Agent/UDP]
#$ns attach-agent $n1 $udp1

#setup cbr traffic
set cbr0 [new Application/Traffic/CBR]
$cbr0 set packetSize 1024
$cbr0 set interval 0.005
$cbr0 attach-agent $udp0

#set cbr1 [new Application/Traffic/CBR]
#$cbr1 set packetSize 1024
#$cbr1 set packetSize 0.005
#$cbr1 attach-agent $udp1

#create traffic sink
set null0 [new Agent/Null]
$ns attach-agent $n2 $null0

#connect udp and null agent
$ns connect $udp0 $null0

#start and stop of data
$ns at 0.5 "$cbr0 start"
#$ns at 1.0 "$cbr1 start"
$ns at 4.5 "$cbr0 stop"
#$ns at 5.0 "$cbr1 stop"
$ns at 5.0 "finish"


#run 
$ns run

