A07
B sends ping request (default of 1 request per second) for 1 minute, takes a break for 30
seconds and again sends ping request for 1 minute (default of 1 request per second) to A. B
implements rate limit using action "-j ACCEPT" for A only and drops echo reply as per X and Y.
No echo request should be dropped. From B send ping packet to some other machine (e.g.
google) for 1 minute, no packets should be dropped. Implement the rate limit and explain the
behaviour.
