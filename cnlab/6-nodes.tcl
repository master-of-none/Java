set ns [new Simulator]                                                           
set nf [open out.nam w]
$ns namtrace-all $nf

proc finish {} {
   global ns nf
   global tchan_
   $ns flush-trace
   close $nf
   exec nam out.nam &
   exit 0 }

set s1 [$ns node]
set s2 [$ns node]
set s3 [$ns node]
set s4 [$ns node]
set r1 [$ns node]
set r2 [$ns node]

$ns color 1 Red

$ns duplex-link $s1 $r1 10mb 2ms DropTail
$ns duplex-link $s2 $r1 10mb 3ms DropTail
$ns duplex-link $r1 $r2 1.5mb 20ms RED
$ns queue-limit $r1 $r2 25
$ns queue-limit $r2 $r1 25
$ns duplex-link $r2 $s3 10mb 4ms DropTail
$ns duplex-link $r2 $s4 10mb 5ms DropTail

$ns duplex-link-op $s1 $r1 orient left-down
$ns duplex-link-op $s2 $r1 orient left-up
$ns duplex-link-op $r2 $s3 orient right-down
$ns duplex-link-op $r2 $s4 orient right-up


set tcp1 [new Agent/TCP]
$ns attach-agent $s1 $tcp1

set tcp2 [new Agent/TCP]
$ns attach-agent $s2 $tcp2


set sink1 [new Agent/TCPSink]
$ns attach-agent $s3 $sink1

set sink2 [new Agent/TCPSink]
$ns attach-agent $s3 $sink2


$ns connect $tcp1 $sink1
$ns connect $tcp2 $sink2

set ftp1 [new Application/FTP]
$ftp1 attach-agent $tcp1

set ftp2 [new Application/FTP]
$ftp2 attach-agent $tcp2
set redq [[$ns link $r1 $r2] queue]
set tchan_ [open all.q w]
$redq trace curq_
$redq trace ave_
$redq attach $tchan_

#$ftp set packetSize_ 500
#$ftp set interval_ 1000


$ns at 0.2 "$ftp1 start"
$ns at 0.5 "$ftp2 start"
$ns at 2.5 "$ftp1 stop"
$ns at 5.0 "$ftp2 stop"



$ns at 3.0 "finish"

$ns run

