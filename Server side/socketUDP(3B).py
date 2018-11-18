import socket
import threading
import sys
bind_ip = socket.gethostname()
bind_port = 8089
# Create a TCP/IP socket
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

sock.bind((bind_ip, bind_port))
print ("Listening on {}:{}".format(bind_ip, bind_port))
n = []
while True:
    d = sock.recvfrom(4096)
    data = d[0]
    address = d[1]
    print("....Recieved packet from client: {}" .format(address))
    m = str(data)[2:len(str(data))-1].split("|.|")
    print(m)
    n = 0
    if "+" in m :
       n = int(m[0]) + int(m[1])
    elif "-" in m:
     n = int(m[0] )- int(m[1])
    elif "*" in m :
      n = int(m[0] )* int(m[1])       
    elif "/"  in m:
      n = int(m[0])/int(m[1]   )     
        #int(n[1])
    sock.sendto((str(n)).encode(), address) 
