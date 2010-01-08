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