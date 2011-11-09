#!/bin/sh

DIR="$( cd -P "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

java -cp $DIR/jgroups-3.0.0.CR5.jar -Djgroups.bind_addr=127.0.0.1 -Djava.net.preferIPv4Stack=true org.jgroups.demos.Draw &
java -cp $DIR/jgroups-3.0.0.CR5.jar -Djgroups.bind_addr=127.0.0.1 -Djava.net.preferIPv4Stack=true org.jgroups.demos.Draw &

