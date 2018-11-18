import socket
import threading
import base64
import pyodbc
import datetime
import time
bind_ip = socket.gethostname()
bind_port = 8088

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind((bind_ip, bind_port))
server.listen(5)  # max backlog of connections
print ("Listening on {}:{}".format(bind_ip, bind_port))
rowdata = []
listClient = []


def _generate_headers(response_code):
        """
        Generate HTTP response headers.
        Parameters:
            - response_code: HTTP response code to add to the header. 200 and 404 supported
        Returns:
            A formatted HTTP header for the given response_code
        """
        header = ''
        if response_code == 200:
            header += 'HTTP/1.1 200 OK\n'
        elif response_code == 404:
            header += 'HTTP/1.1 404 Not Found\n'

        time_now = time.strftime("%a, %d %b %Y %H:%M:%S", time.localtime())
        #header += 'Date: {now}\n'.format(now=time_now)
        header += 'Server: Simple-Python-Server\n'
        header += 'Connection: close\n\n' # Signal that connection will be closed after completing the request
        return header

def handle_client_connection(client_socket,address):  
 while True:
  try  :
    request = client_socket.recv(1024)
    print(datetime.datetime.now())
    print("....Recieved packet from client: {}" .format(address))
    request_method = request.decode().split(' ')[0]
    content_dir = "web"
    data = request.decode()
    if request_method == "GET" or request_method == "HEAD":
                print(request)
                print("Method: {m}".format(m=request_method))
                print("Request Body: {b}".format(b=data))
                # Ex) "GET /index.html" split on space
                file_requested = data.split(' ')[1]

                # If get has parameters ('?'), ignore them
                file_requested =  file_requested.split('?')[0]
                
                if file_requested == "/":
                    file_requested = "/1.html"
                elif file_requested == "/2.html":
                    file_requested = "/2.html"
                elif file_requested == "/example.xml":
                    file_requested = "/example.xml"
                elif file_requested == "/example2.xml":
                    file_requested = "/example2.xml"
                filepath_to_serve = content_dir + file_requested
                print("Serving web page [{fp}]".format(fp=filepath_to_serve))

                # Load and Serve files content
                try:
                    f = open(filepath_to_serve, 'rb')
                    if request_method == "GET": # Read only for GET
                        response_data = f.read()
                    f.close()
                    response_header = _generate_headers(200)

                except Exception as e:
                    print("File not found. Serving 404 page.")
                    response_header = _generate_headers(404)
                    if request_method == "GET": # Temporary 404 Response Page
                        response_data = b"<html><body><center><h1>Error 404: File not found</h1></center><p>Head back to <a href='/'>dry land</a>.</p></body></html>"
                response = response_header.encode()
                
                if request_method == "GET":
                    response += response_data
                #listClient.remove(client_socket)
                client_socket.send(response)
                client_socket.close()
                break
    else:
     listClient.append(client_socket)
     n = base64.b64decode(str(request.decode()))
     m = str(n)[2:len(str(n))-1].split("|.|")
     k = 0
     print(m)
     if len(m) == 4 :
      if m[3] == "Caculate" :
       if m[2] == "+" :
        print("+++")
        print(int(m[0]) + int(m[1]))
        client_socket.send('%rXTX'.encode() %(base64.b64encode(str(int(m[0])+int(m[1])).encode())))
        #client_socket.send(str(int(m[0])+int(m[1])).encode())    
       elif m[2] == "-" :
        client_socket.send('%rXTX'.encode() %(base64.b64encode(str(int(m[0])-int(m[1])).encode())))
       elif m[2] == "*" :
               client_socket.send('%rXTX'.encode() %(base64.b64encode(str(int(m[0])*int(m[1])).encode())))
       elif m[2] == "/" :
               client_socket.send('%rXTX'.encode() %(base64.b64encode(str(int(m[0])/int(m[1])).encode())))
       #client_socket.send('\n'.encode())
       client_socket.send('%rXTX'.encode() %(base64.b64encode("\n").encode()))
       client_socket.close()
       print(".......Closing connection........")
       break
     if m[0] == "USCLN" :
       m[1] = int(m[1])
       m[2] = int(m[2])
       while(m[1] != m[2] ) :
         if(m[1] > m[2]) :
                  m[1]= m[1]-m[2]
         else :
            m[2] = m[2] = m[1]
       print(m[1])
       client_socket.send('%r'.encode() %(str(m[1]).encode()))
       client_socket.close()
  except:
    print("Client terminate connection!!!")
    client_socket.close()
    break

while True:
    client_sock, address = server.accept()
    print ('Accepted connection from {}:{}'.format(address[0], address[1]))
    client_handler = threading.Thread(
        target=handle_client_connection,
        # without comma you'd get a... TypeError: handle_client_connection() argument after * must be a sequence, not _socketobject
        args=(client_sock,address[0],)  
    )
    client_handler.start() 
