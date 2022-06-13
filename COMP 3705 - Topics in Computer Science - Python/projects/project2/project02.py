'''project 2'''
import random

def new_board(x_dimension, y_dimension):
    return [[False for i in range(x_dimension)] for j in range(y_dimension)]

def randomize_board(board):
    for i in range(len(board)):
        for j in range(len(board[i])):
            random_n = random.random()
            if random_n >= 0.5:
                cell_value = True
            elif random_n < 0.5:
                cell_value = False
            board[i][j] = cell_value

def board_to_string(board):
    array_string =""
    for i in range(len(board)):
        for j in range(len(board[i])):
            if board[i][j] is True:
                array_string += "X "
            elif board[i][j] is False:
                array_string += "O "
        array_string = array_string[:-1]
        array_string += "\n"
    print(array_string)
    return array_string
def flip_cell(board, x, y):
    last_row = ((len(board))-1)
    last_col = ((len(board[0]))-1)
    if x <= last_col and y <= last_row:
        if board[y][x] is True:
            board[y][x] = False
        else:
            board[y][x] = True
        if y == 0:
            if board[y+1][x] is True:
                board[y+1][x] = False
            else:
                board[y+1][x] = True

        elif y == last_row:
            if board[y-1][x] is True:
                board[y-1][x] = False
            else:
                board[y-1][x] = True
        elif y != 0 and y != last_row:
            if board[y+1][x] is True:
                board[y+1][x] = False
            else:
                board[y+1][x] = True
            if board[y-1][x] is True:
                board[y-1][x] = False
            else:
                board[y-1][x] = True
        if x == 0:
            if board[y][x+1] is True:
                board[y][x+1] = False
            else:
                board[y][x+1] = True

        elif x == last_col:
            if board[y][x-1] is True:
                board[y][x-1] = False
            else:
                board[y][x-1] = True
        elif x != 0 and x != last_col:
            if board[y][x+1] is True:
                board[y][x+1] = False
            else:
                board[y][x+1] = True

            if board[y][x-1] is True:
                board[y][x-1] = False
            else:
                board[y][x-1] = True
    else:
        print("Wrong entry")
def is_winning(board):
    all_false = all(all(item is False for item in items) for items in board)
    all_True = all(all(item is True for item in items) for items in board)
    if all_false is True:
        return True
    elif all_True is True:
        return True

    return False

if __name__ == '__main__':
    X_DIM = int(input("enter x dimension: "))
    Y_DIM = int(input("enter y dimension: "))
    BOARD_ARRAY = new_board(X_DIM, Y_DIM)
    randomize_board(BOARD_ARRAY)
    ARRAY_TO_STRING = board_to_string(BOARD_ARRAY)
    while is_winning(BOARD_ARRAY) is not True:
        X_CORD = int(input("enter X cord of cell you want to flip:"))
        Y_CORD = int(input("enter Y cord of cell you want to flip:"))
        flip_cell(BOARD_ARRAY, X_CORD, Y_CORD)
        ARRAY_IN_STRING = board_to_string(BOARD_ARRAY)
        if is_winning(BOARD_ARRAY) is True:
            print("You won!!")
            break
