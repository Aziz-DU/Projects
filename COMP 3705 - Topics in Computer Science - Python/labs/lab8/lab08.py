from peewee import *
from playhouse.dataset import DataSet

db = DataSet('sqlite:///:memory:')
table = db['lab8']
table.thaw(format='csv', filename='Accidental_Drug_Related_Deaths_2012-2018.csv')
#table.all()
peewee_users = db['lab8'].find(Fentanyl = 'Y', Cocaine = 'Y', Methadone = 'Y', Heroin = 'Y', Benzodiazepine = 'Y', Sex = 'Male', Age = '34')
count = 1
#query=
for pu in peewee_users:
	print(pu)
#	count+=1
#print(peewee_users.PersonID) 
#print()
