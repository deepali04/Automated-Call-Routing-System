# Automated-Call-Routing-System
A simplified version of mobile phone tracking system was implemented to handle queries for adding mobile phones over local exchange areas, changing their location and finding lowest routing path to establish a connection between two mobile phones.


## Mobile phone tracking system description
As we know each mobile phone that is switched on is connected to the base station which is nearest. These base stations are popularly called cell phone towers. Although sometimes we may be within range of more than one base station, each phone is registered to exactly one base station at any point of time. When the phone moves from the area of one base station to another, it will be de-registered at its current base station and re-registered at new base station.

## Making a phone call
When a phone call is made from phone p1 registered with base station b1 to a phone p2 , the first thing that the base station b1 has to do is to find the base station with which p2 is registered. For this purpose there is an elaborate technology framework that has been developed over time. You can read more about it on the Web. But, for now, we will assume that b1 sends a query to a central server C which maintains a data structure that can answer the query and return the name of the base station, let’s call it b2 , with which p2 is registered. C will also send some routing information to b1 so that b1 can initiate a call with b2 and, through the base stations p1 and p2 can talk. It is the data structure at C that we will be implementing.
