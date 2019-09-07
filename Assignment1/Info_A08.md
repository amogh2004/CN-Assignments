# All the information regarding Question A08
**STATUS CODE 200** </br>
This class of status codes indicates the action requested by the client was received, understood and accepted. 200 OK. Standard response for successful HTTP requests. The actual response will depend on the request method used.</br></br>
**STATUS CODE 301** </br>
A status code of 301 tells a client that the resource they asked for has permanently moved to a new location. The response should also include this location. It tells the client to use the new URL the next time it wants to fetch the same resource.</br>
The 301 response from the Web server should always include an alternative URL to which redirection should occur. If it does, a Web browser will immediately retry the alternative URL. So you never actually see a 301 error in a Web browser, unless perhaps you have a corrupt redirection chain e.g. URL A redirects to URL B which in turn redirects back to URL A. If your client is not a Web browser, it should behave in the same way as a Web browser i.e. immediately retry the alternative URL.</br>
If the Web server does not return an altern correctly.tinave URL with the 301 response, then either the Web server software itself is defective or the Webmaster has not set up the URL redirection</br></br>
**STATUS CODE 403**</br>
The 403 (Forbidden) status code indicates that the server understood the request but refuses to authorize it. A server that wishes to make public why the request has been forbidden can describe that reason in the response payload (if any). Thus, a 403 (or a 404) might now mean about anything.</br></br>
