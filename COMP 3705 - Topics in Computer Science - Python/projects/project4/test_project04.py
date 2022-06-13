''' Module project04.py '''
import pytest
from project04 import cypher_alphabet, encrypt, get_unencrypted_text

def test_cypher_alphabet():
    word= "Mother board"
    new_string=cypher_alphabet(word)
    assert new_string == "motherbadcfgijklnpqsuvwxyz"
def test_encrypt():
    cypher_alphabet="motherbadcfgijklnpqsuvwxyz"
    text="This is a file. This file has some WORDS in it."
    encrypted_message = encrypt(text, cypher_alphabet)
    assert encrypted_message == "Sadq dq m rdge. Sadq rdge amq qkie WKPHQ dj ds."
def test_get_unencrypted_text():
    file_path=get_unencrypted_text("test_file.txt")
    assert file_path == "This is a file. This file has some WORDS in it."
