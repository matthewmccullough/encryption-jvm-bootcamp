* Steps:
sudo port install squid (or squid3)
sudo port install imagemagick
(for mogrify)

* Copy the redirect script named similar to the following:
/opt/local/etc/squid/matthew_image_flip.redir

* Point to it in the Squid config file:
/opt/local/etc/squid/squid.conf
with the following command
redirect_program /opt/local/etc/squid/matthew_image_flip.redir

* Change permission on matthew_image_flip.redir to be excutable for everyone
* Update the matthew_image_flip.redir script to point to your servable (shared) web server directory:
/Users/mccm06/sites/junkimages (in this case)

* Set up the caches
squid -z

* Change permissions on the cache and log directories to RWX for everyone
cd /opt/local/var/squid/cache
find . -type d -exec chmod a+wrx {} \;
find . -type f -exec chmod a+wr {} \;

* Restart squid
squid -k kill
squid

* Tail the log to see if you have any errors
tail -f /opt/local/var/squid/logs/*

* Set up your browser to use a HTTP proxy server of localhost:3128

*Disable the proxy server and also shut down squid when done