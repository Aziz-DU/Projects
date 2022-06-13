'''project03, Aziz'''
import random

def new_board():
    letters = [
        'A',
        'B',
        'C',
        'D',
        'E',
        'F',
        'G',
        'H',
        'I',
        'J',
        'K',
        'L',
        'M',
        'N',
        'O',
        'P',
        'Q',
        'R',
        'S',
        'T',
        'U',
        'V',
        'W',
        'X',
        'Y',
        'Z',
        ]
    new_board_list = []
    for i in range(4):
        row = []
        for j in range(4):
            random_Cha = random.randint(0, 25)
            row.append(letters[random_Cha])
        new_board_list.append(row)
    return new_board_list


def board_to_string(board):
    array_string = ''
    for i in range(len(board)):
        for j in range(len(board[i])):
            array_string += '[' + board[i][j] + '] '
        array_string = array_string[:-1]
        array_string += '\n'
    array_string = array_string[:-1]
    return array_string


def board_has_word(board, word, used_cells=None):
    word = word.upper()
    first_letter = word[0]
    first_letter_indx = cells_with_letter(board, first_letter)
    letters_are_connected = False
    result = None
    if used_cells is None or not first_letter_indx:
        if not first_letter_indx:
            print("word doesn't exist")
            return False
        else:
            used_cells = []
            backup_cells = [element for element in used_cells]
            for s in first_letter_indx:
                used_cells.append(list(tuple(s)))
                result = board_has_word(board, word[1:], used_cells)
                used_cells = [element for element in backup_cells]
                if result is True:
                    return True
    else:
        already_used = [i for i in first_letter_indx if i in used_cells]
        if already_used:  # #####################if len(already_used) > 0:
            for c in already_used:
                first_letter_indx.remove(c)
        if result is not True:
            backup_cells = [element for element in used_cells]
            word_reset = word
            if len(first_letter_indx) > 1:
                for s in first_letter_indx:
                    if result is not True:
                        if s == used_cells[-1]:
                            first_letter_indx.remove(s)
                        adj_cells = adjacent_cells(s)
                        used_cells[-1] = tuple(used_cells[-1])
                        for i in adj_cells:
                            if i == used_cells[-1]:
                                letters_are_connected = True
                                adj_cells.remove(i)
                        if letters_are_connected:
                            used_cells.append(s)
                            word = word[1:]
                            if not word:
                                print('The word exists')
                                result = True
                                return True
                            else:
                                return board_has_word(board, word,
                                        used_cells)
                    used_cells = [element for element in backup_cells]
                    word = word_reset
                    letters_are_connected = False
                    result = False
            elif len(first_letter_indx) == 1:

                adj_cells = adjacent_cells(first_letter_indx[0])
                used_cells[-1] = tuple(used_cells[-1])
                for i in adj_cells:
                    if i == used_cells[-1]:
                        letters_are_connected = True
                        adj_cells.remove(i)
                if letters_are_connected:
                    used_cells.append(first_letter_indx[-1])
                    word = word[1:]
                    if not word:
                        print('The word exist')
                        result = True
                        return True
                    else:
                        return board_has_word(board, word, used_cells)
    if result:
        return True
    elif result is None:
        return False


def adjacent_cells(current_cell):
    x = current_cell[0]
    y = current_cell[1]
    temp_list = []
    last_row = len(A_NEW_BOARD) - 1
    last_col = len(A_NEW_BOARD[0]) - 1
    (top_E, bottom_E, right_E, left_E) = (False, False, False, False)
    if x <= last_col and y <= last_row:
        if y == 0:  # on top
            top_E = True
        elif y == last_row:

                           # on bottom

            bottom_E = True
        if x == 0:  # left:
            left_E = True
        elif x == last_col:

                           # right

            right_E = True

        if top_E and right_E:  # top right - only left diagonal
            temp_list.append([x - 1, y])
            temp_list.append([x, y + 1])
            temp_list.append([x - 1, y + 1])
        elif top_E and left_E:

                              # top left - only right diagonal

            temp_list.append([x + 1, y])
            temp_list.append([x, y + 1])
            temp_list.append([x + 1, y + 1])
        elif top_E and right_E is False and left_E is False:

                                                            # both diag

            temp_list.append([x + 1, y])
            temp_list.append([x - 1, y])
            temp_list.append([x + 1, y + 1])
            temp_list.append([x - 1, y + 1])
            temp_list.append([x, y + 1])
        elif bottom_E and right_E:

                                  # bottom right - only left diagonal

            temp_list.append([x - 1, y])
            temp_list.append([x, y - 1])
            temp_list.append([x - 1, y - 1])
        elif bottom_E and left_E:

                                 # bottom left - only right diagonal

            temp_list.append([x + 1, y])
            temp_list.append([x, y - 1])
            temp_list.append([x + 1, y - 1])
        elif bottom_E and right_E is False and left_E is False:

                                                               # both diag

            temp_list.append([x + 1, y])
            temp_list.append([x - 1, y])
            temp_list.append([x + 1, y - 1])
            temp_list.append([x - 1, y - 1])
            temp_list.append([x, y - 1])
        elif right_E and top_E is False and bottom_E is False:

                                                              # left diag

            temp_list.append([x - 1, y])
            temp_list.append([x - 1, y + 1])
            temp_list.append([x - 1, y - 1])
            temp_list.append([x, y - 1])
            temp_list.append([x, y + 1])
        elif left_E and top_E is False and bottom_E is False:

                                                             # right diag

            temp_list.append([x + 1, y])
            temp_list.append([x + 1, y + 1])
            temp_list.append([x + 1, y - 1])
            temp_list.append([x, y - 1])
            temp_list.append([x, y + 1])
        elif left_E is False and right_E is False and top_E is False \
            and bottom_E is False:

                                                                                           # all diagonals

            temp_list.append([x, y - 1])
            temp_list.append([x, y + 1])
            temp_list.append([x + 1, y])
            temp_list.append([x - 1, y])
            temp_list.append([x + 1, y + 1])
            temp_list.append([x + 1, y - 1])
            temp_list.append([x - 1, y - 1])
            temp_list.append([x - 1, y + 1])
    else:
        print('Wrong entry')
    adjacent_tuple = [tuple(l) for l in temp_list]
    return adjacent_tuple


def cells_with_letter(board, letter):
    temp_list = []
    for i in range(4):
        for j in range(4):
            if board[i][j] == letter:
                temp_list.append([j, i])
    indices_of_letter = [tuple(l) for l in temp_list]
    return indices_of_letter

A_NEW_BOARD = new_board()
STRING_BOARD = board_to_string(A_NEW_BOARD)

if __name__ == '__main__':
    print(A_NEW_BOARD)
    print(STRING_BOARD)
    STILL_ACTIVE = True
    while STILL_ACTIVE:
        INPUT_WORD = str(input('word:'))
        CHECK_WORD = board_has_word(A_NEW_BOARD, INPUT_WORD)
        print(CHECK_WORD)

