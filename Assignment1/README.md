A08. Implement web access including redirect.
Write a web client to access a webpage on a web server (e.g. cse.ksit.edu.in). If the server redirects the request to another URL using status code 301 or 302, then client should access the new URL. Repeat the process till status code received is 200 or 403 or 404. Save the content of the received file in your local directory as per path given in the redirected URL and also note down the Last-Modified header for this file.  The program should not use any URL library, but should use HTTP protocol headers directly as per specs. The program should accept following parameter: URL.
The submission should include tcpdump of communication and saved file using redirect.
<br/><br/>
B02. Create web pages and corresponding web requests that result in following status codes: 200, 302, 404, 403.
<br/><br/>
B05. Identify a web page that provides E-tag header. Access this web page with request header "If-None-Match:" and provide the etag value and verify the status code as 304. Access the same web page but modified value of Etag in the header "If-None-Match:" and verify the status code 200.
<br/>

