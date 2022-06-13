'''''Project04.py'''''
def main():
    word_input = str(input('Please enter a keyword for the mixed cipher: '))
    cypher_string = cypher_alphabet(word_input)
    text_file = get_unencrypted_text("test_file.txt")
    print(encrypt(text_file, cypher_string))

def cypher_alphabet(word):
    plain_text = "abcdefghijklmnopqrstuvwxyz"
    print("Plaintext: ", plain_text)
    new_string = ""
    for i in word:
        i = i.lower()
        if i not in new_string and i.isalpha() is True:
            new_string += i
    for i in plain_text:
        if i not in new_string:
            new_string += i
    print("Ciphertext: ", new_string)
    return new_string
def encrypt(text, cypher_text):
    encrypted_message = ""
    plain_text = "abcdefghijklmnopqrstuvwxyz"
    capitalize_letter = False
    for i in text:
        if i.isalpha() is True:
            if i.isupper() is True:
                capitalize_letter = True
            else:
                capitalize_letter = False
            i = i.lower()
            index_pos = plain_text.find(i)
            cypher_encypt = cypher_text[index_pos]
            if capitalize_letter:
                cypher_encypt = cypher_encypt.upper()
            encrypted_message += cypher_encypt
        else:
            encrypted_message += i
    return encrypted_message
def get_unencrypted_text(file_path):
    file_content = ""
    try:
        f = open(file_path, "r")
        file_content = f.read()
    except FileNotFoundError:
        print("Wrong file name")
    except IOError:
        print("Problem accessing the file")
    file_content = file_content.rstrip()
    return file_content

if __name__ == '__main__':
    main()
