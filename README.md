# SpringBoot-RabbitMQ

  An SpringBoot application that takes the json object and forward it to the repective queues based on "Country" property in the json object.


  Commands needed:

  To set Time-to-Live and Expiration policies the queues

  $ rabbitmqctl set_policy TTL ".*" '{"message-ttl":60000}' --apply-to queues

  $ rabbitmqctl set_policy expiry ".*" '{"expires":1800000}' --apply-to queues
    
     60000(*60sec) is alterable.
 
  To set an alternate exchange to default exchange

  $ rabbitmqctl set_policy AE "^ABC$" '{"alternate-exchange":"XYZ"}':sasasa

    ABC - default exchange. 
    XYZ - Exchange to which unrouted message has to be routed.


