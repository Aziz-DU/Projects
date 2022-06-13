'''lab06.py'''
def reverse_iter(iter_obj):
    class Reverse:
        def __init__(self, iter_object):
            self.iter_object = iter_object
            self.index = len(iter_object)
        def __iter__(self):
            return self
        def __next__(self):
            if self.index == 0:
                raise StopIteration
            self.index = self.index - 1
            return self.iter_object[self.index]
    rev = Reverse(iter_obj)
    return rev
def return_logger(func):
    def func_wrapper(*args, **kwargs):
        val = func(*args, **kwargs)
        print("Function returned:", func(*args, **kwargs))
        return val
    return func_wrapper
def get_fib_generator():
    a, b = 0, 1
    while True:
        a, b = b, a + b
        yield a
