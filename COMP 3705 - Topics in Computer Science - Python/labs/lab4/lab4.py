def test(mylist):
    return [x for x in mylist if isinstance(x,str) if x.startswith("aziz")]

mylist=["azizhi",2,"bye",5,"azizfuck","no",4]

#xx=list(filter(lambda x:[x for x in mylistz if isinstance(x,str) if
#x.startswith("aziz")],mylistz))
mylist[:]=list(filter(lambda x:str(x).startswith("aziz"),mylist))
xx=[x for x in mylist if str(x).startswith("aziz")]
matrix = [[j**(i+1) for j in range(1,5)] for i in range(4)] 
mylist[:]=list(filter(lambda x:str(x).startswith("aziz"),mylist))
[x for x in mylist if str(x).startswith("aziz")]

print(matrix)
