#include <stdio.h>	//printf
#include <string.h>	//strlen
#include <sys/socket.h>	//socket
#include <arpa/inet.h>	//inet_addr
#include <unistd.h>

int main(int argc , char *argv[])
{
	int sock;
	struct sockaddr_in server;
	
	sock = socket(AF_INET , SOCK_STREAM , 0);
	if (sock == -1)
	{
		printf("Could not create socket");
	}
	puts("Socket created");
	
	server.sin_addr.s_addr = inet_addr("127.0.0.1");
	server.sin_family = AF_INET;
	server.sin_port = htons( 8888 );

	if (connect(sock , (struct sockaddr *)&server , sizeof(server)) < 0)
	{
		perror("connect failed. Error");
		return 1;
	}
	
	puts("Connected\n");

int range_start = 0;

int return_status = read(sock, &range_start, sizeof(range_start));
if (return_status > 0) {
   fprintf(stdout, "range_start = %d\n", ntohl(range_start));
}
else {
    printf("ERROR reading from socket\n");
}

int range_stop = 0;

return_status = read(sock, &range_stop, sizeof(range_stop));
if (return_status > 0) {
    fprintf(stdout, "Received int = %d\n", ntohl(range_stop));
}
else {
    printf("ERROR reading from socket\n");
}   

int currentCount=0;
 for (int i = ntohl(range_start); i < ntohl(range_stop); i++) {
            int j = 2;
            while ((i % j) != 0 && j <= i / 2) {
                j++;
            }
            if ((i % j) != 0) {
                currentCount++;
            }
        }

int currThreadCount = htonl(currentCount);
write(sock, &currThreadCount, sizeof(currThreadCount));
		
      
	close(sock);
	return 0;
}
