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
m = []
s= []
ed = []
m1= ""
s1 =""
ed1 = ""
class Client :
    address = ""
    S = ""
    N = 0
    def __init__(self, address):
       self.address = address
    def setS(self, S):
       self.S = S
    def setN(self, N):
        self.N = N
while True:
    d = sock.recvfrom(4096)
    data = d[0]
    address = d[1]
    print("....Recieved packet from client: {}" .format(address))
    print(data)
    print(address[0])
    if address not in n :
     n.append(address[0])
     array  = data.decode().split("|.|")
     if array[0] == "e-d":
         #ed1 = str(address+"|.|"+data)
         ed.append(str(address[0])+"|.|"+str(data))
     elif array[0] == "S" :
         #s1 =str(address+"|.|"+data)
         s.append(str(address[0])+"|.|"+str(data))
     elif array[0] == "N" :
         #m1 = str(address+"|.|"+data)
         m.append(str(address[0])+"|.|"+str(data))   
    else :
     if array[0] == "e-d":
          #         ed = str(address+"|.|"+data)
         ed.append(str(address[0])+"|.|"+str(data))
     elif array[0] == "S" :
           #        s=str(address+"|.|"+data)
         s.append(str(address[0])+"|.|"+str(data))
     elif array[0] == "N" :
            #       m=str(address+"|.|"+data)
           m.append(str(address[0])+"|.|"+str(data))
    if len(s)> 0 and len(m)>0 and len(ed)>0 :
      s2 = s[0].split("|.|")[2]
      s2  = s2[0:len(str(s2))-1]
      m2 = m[0].split("|.|")[2]
      m2  = m2[0:len(str(m2))-1]
      ed2 = ed[0].split("|.|")[2]
      ed2 = ed2[0:len(str(ed2))-1]
      if(ed2 == "0") :
             #print(s2[m2])
             num = ord(s2[int(m2)])
             e = chr(num+int(m2))
             s2 = s2[:int(m2)] + e + s2[int(m2)+1:]
             sock.sendto((s2).encode(), address)
             del s[0],m[0],ed[0],n[0]
      elif(ed2 == "1") :
             #print(s2[m2])
             num = ord(s2[int(m2)])
             e = chr(num-int(m2))
             s2 = s2[:int(m2)] + e + s2[int(m2)+1:]
             sock.sendto((s2).encode(), address)
             del s[0],m[0],ed[0],n[0]
    #listclient.append(client_handler)
    
