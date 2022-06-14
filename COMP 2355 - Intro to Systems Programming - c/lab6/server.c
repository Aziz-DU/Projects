#include<stdio.h>
#include<string.h>	//strlen
#include<stdlib.h>	//strlen
#include<sys/socket.h>
#include<arpa/inet.h>	
#include<unistd.h>	//write
#include<pthread.h> 

void *connection_handler(void *);
	  int totalCount=0;
      int start=0;
      int stop=0;
      int threadCount=0;
      int numThreads=5 ;   
int main(int argc , char *argv[])
{

    
	int socket_desc , client_sock , c , *new_sock;
	struct sockaddr_in server , client;
	
	//Create socket
	socket_desc = socket(AF_INET , SOCK_STREAM , 0);
	if (socket_desc == -1)
	{
		printf("Could not create socket");
	}
	
	server.sin_family = AF_INET;
	server.sin_addr.s_addr = INADDR_ANY;
	server.sin_port = htons( 8888 );
	
	if( bind(socket_desc,(struct sockaddr *)&server , sizeof(server)) < 0)
	{
		perror("bind failed. Error");
		return 1;
	}

	listen(socket_desc , 3);
	puts("Waiting for incoming connections...");
    
	c = sizeof(struct sockaddr_in);
	  
    int rangeStart=1000 ;    //1000->100000
    int rangeEnd =100000;
	while((client_sock = accept(socket_desc, (struct sockaddr *)&client, (socklen_t*)&c)) )
	{
           
		 start = (((rangeEnd - rangeStart) / numThreads) * threadCount) + rangeStart;
         stop = (((rangeEnd - rangeStart) / numThreads) * (threadCount + 1)) + rangeStart;
        
        printf("thread number %d range from %d to %d \n",threadCount,start,stop);
	
        threadCount++;
		puts("Connection accepted");
		pthread_t sniffer_thread;
		new_sock = malloc(1);
		*new_sock = client_sock;
		
		if( pthread_create( &sniffer_thread , NULL ,  connection_handler, (void*) new_sock) < 0)
		{
			perror("could not create thread");
			return 1;
		}
	}
	
	if (client_sock < 0)
	{
		perror("accept failed");
		return 1;
	}
	
	return 0;
}

void *connection_handler(void *socket_desc)
{
    
	int sock = *(int*)socket_desc;


int send_start = htonl(start);
write(sock, &send_start, sizeof(send_start));
		
		
	int send_stop = htonl(stop);
write(sock, &send_stop, sizeof(send_stop));
		
int currentCount = 0;

int return_status = read(sock, &currentCount, sizeof(currentCount));
if (return_status > 0) {
    fprintf(stdout, "Thread returned  %d\n", ntohl(currentCount));
}
else {
    printf("ERROR reading from socket\n");
}   
totalCount+= ntohl(currentCount);

free(socket_desc);

     if(threadCount>=numThreads){
        printf("all threads finished and total is = %d \n",totalCount);
        close(sock);
           exit(0);
            }	
	return 0;
}
