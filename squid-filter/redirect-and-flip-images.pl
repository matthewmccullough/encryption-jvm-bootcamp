#!/usr/bin/perl

#Details at:
# http://www.ex-parrot.com/%7Epete/upside-down-ternet.html

#!/usr/bin/perl

#Details at:
# http://www.ex-parrot.com/%7Epete/upside-down-ternet.html

$|=1;
$count = 0;
$pid = $$;
while (<>) {
        chomp $_;
        if ($_ =~ /(.*\.jpg)/i) {
                $url = $1;
                system("/opt/local/bin/wget", "-q", "-O","/Users/mccm06/sites/junkimages/$pid-$count.jpg", "$url");
                system("/opt/local/bin/mogrify", "-swirl", "90","/Users/mccm06/sites/junkimages/$pid-$count.jpg");
                system("/bin/chmod", "a+r", "/Users/mccm06/sites/junkimages/$pid-$count.jpg");
                print "http://127.0.0.1/~mccm06/junkimages/$pid-$count.jpg\n";
        }
        elsif ($_ =~ /(.*\.gif)/i) {
                $url = $1;
                system("/opt/local/bin/wget", "-q", "-O","/Users/mccm06/sites/junkimages/$pid-$count.gif", "$url");
                system("/opt/local/bin/mogrify", "-flip","/Users/mccm06/sites/junkimages/$pid-$count.gif");
                system("/bin/chmod", "a+r", "/Users/mccm06/sites/junkimages/$pid-$count.gif");
                print "http://127.0.0.1/~mccm06/junkimages/$pid-$count.gif\n";

        }
        elsif ($_ =~ /(.*\.png)/i) {
                $url = $1;
                system("/opt/local/bin/wget", "-q", "-O","/Users/mccm06/sites/junkimages/$pid-$count.png", "$url");
                system("/opt/local/bin/mogrify", "-rotate", "15","/Users/mccm06/sites/junkimages/$pid-$count.png");
                system("/bin/chmod", "a+r", "/Users/mccm06/sites/junkimages/$pid-$count.png");
                print "http://127.0.0.1/~mccm06/junkimages/$pid-$count.png\n";

        }
        else {
                print "$_\n";;
        }
        $count++;
}