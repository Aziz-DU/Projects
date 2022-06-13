import time
import sys
import os
def log(file_name = None):
	def decorator_function(original_function):
		def wrapper_function(*args):
			start_time = time.time()
			if file_name:
				file_accessible = os.path.isfile(file_name)
				if file_accessible:
					sys.stdout = open(file_name, 'a')
			print("Calling function", original_function.__name__)
			if len(args) < 1:
				print("No arguments")
			else:
				print("Arguments: ")
				for i in args:
                    			print(i, " of ", type(i))
			print("output: ")
			result = original_function(*args)
			execution_time = float(time.time() - start_time)
			formated_exec_time = '{0:.5f}'.format(execution_time)
			print("Execution time: ", formated_exec_time, " s.")
			if result is not None:
				print("Return value: ", result, " of type ", type(result))
			else:
				print("No return value")
			sys.stdout = sys.__stdout__
			return result
		return wrapper_function
	return decorator_function

