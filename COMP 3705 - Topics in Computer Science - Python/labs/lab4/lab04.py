'''lab03.py'''
def problem_1(numbers_list, number):
    return [x for x in numbers_list if not x % number]
def problem_2(numbers_list, number):
    return [x for x in numbers_list if str(x).find(str(number)) != -1]
def problem_3(str1, char_list):
    return ''.join([c for c in str1 if c not in char_list])
def problem_4(numbers_list):
    temp_list = [x for x in numbers_list for j in range(2, 10) if x % j == 0]
    return [i for n, i in enumerate(temp_list) if i not in temp_list[:n]]
