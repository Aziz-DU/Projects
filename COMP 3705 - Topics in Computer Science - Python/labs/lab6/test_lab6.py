''' Module test_lab06.py '''
import pytest
import sys
from lab06 import reverse_iter, return_logger, get_fib_generator

def test_problem01():
    it = reverse_iter([1, 2, 3, 4])

    assert next(it) == 4
    assert next(it) == 3
    assert next(it) == 2
    assert next(it) == 1
    try:
        assert next(it) == 0
    except Exception as e:
        assert e.__class__ == StopIteration

def test_problem02(capsys):
    @return_logger
    def myfunc(a, b, c):
        return a + b + c

    val = myfunc(4, 5, 6)
    out, err = capsys.readouterr()

    assert val == 15
    assert "Function returned: 15" in out

def test_problem03():
    fib_gen = get_fib_generator()

    assert next(fib_gen) == 1
    assert next(fib_gen) == 1
    assert next(fib_gen) == 2
    assert next(fib_gen) == 3
    assert next(fib_gen) == 5
    assert next(fib_gen) == 8
