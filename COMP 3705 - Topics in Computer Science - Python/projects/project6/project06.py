from datetime import datetime
import requests

def main():
	user_name = str(input('Enter username: '))
	get_user_comments(user_name)
def get_user_comments(username):
	dict_list=[]
	for i in range(100):
		r=requests.get("http://imgur.com/user/"+username+"/index/newest/page/"
		               +str(i)+"/hit.json?scrolling")
		page_content=r.text
		if len(page_content) < 1:
			break
		else:
			cc=r.json()["data"]["captions"]["data"]
			dict_list.append(cc)
			continue
	extract_top_5(dict_list)

def extract_top_5(list_of_dict):
	top_comments={}
	list_of_top=[]
	for i in list_of_dict:
		for x in range(len(i)):
			i[x]["datetime"]=datetime.strptime(i[x]["datetime"],'%Y-%m-%d %H:%M:%S')
			top_comments[i[x]["points"]] = [i[x]["hash"],i[x]["title"],i[x]["datetime"]]

	sorted_dict = {k : top_comments[k] for k in sorted(top_comments, reverse=True)}
	first5dict = {k: sorted_dict[k] for k in list(sorted_dict)[:5]}

	for i in first5dict:		
		temp_dic={"hash":first5dict[i][0],"Points":i,
		          "Title":first5dict[i][1],"Date":first5dict[i][2]}

		list_of_top.append(temp_dic)
	count=1;
	for i in list_of_top:#matching format with one provided in the project example
		for y in i:
			if y == 'hash':
				print(str(count)+".",i[y])
				count+=1
			else:
				print(str(y)+":",i[y])
		print()
		
	return list_of_top
if __name__ == '__main__':
    main()	
