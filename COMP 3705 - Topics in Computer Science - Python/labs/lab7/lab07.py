import socket
import threading
import random
class HighCardServer:
	address = 0
	port = 0
	backlog = 0
	def __init__(self, address, port, backlog):
		self.address = address
		self.port = port
		self.backlog = backlog
		self.socket = socket.socket()
	def start_server(self):
		self.socket.bind((str(self.address), self.port))
		self.socket.listen(self.backlog)
		t = threading.Thread(target = self.client_connections)
		t.start()	
	def client_connections(self):
		while True:
			c, a = self.socket.accept()
			self.card_game(c)
	def card_game(self, client):
		cards = {2: 2, 3: 3, 4: 4, 5: 5, 6: 6, 7: 7, 8: 8,
           9: 9, 10: 10, "J": 11, "Q": 12, "K": 13, "A": 14}
		key_list = list(cards.keys()) 
		val_list = list(cards.values()) 
		player_card = random.choice(val_list)
		comp_card = random.choice(val_list)
		p_key = key_list[val_list.index(player_card)]
		c_key = key_list[val_list.index(comp_card)]
		response1 = "Hello, welcome to the High Card Server!\n"
		response2 = "The dealer has " + str(c_key) + "\n"
		response3 = "You have " + str(p_key) + "\n"
		if  player_card > comp_card:
			response4 = "You win!\n" 
		elif player_card == comp_card:
			response4 = "it's a tie!\n"
		elif player_card < comp_card:
			response4 = "The dealer wins!\n"
		client.send(response1.encode())
		client.send(response2.encode())
		client.send(response3.encode())
		client.send(response4.encode())
		client.close()	
