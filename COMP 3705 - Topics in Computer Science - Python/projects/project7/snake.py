snake = []
direction = "down"
apple = []
score = 0

def setup():
    global snake, apple
    
    frameRate(10) # slow it down to make it easier to play
    size(400, 400)
    
    snake.append([50, 50]) # start with a single cell as the head
    generate_new_apple() # make the first apple
    
def draw():
    global score
    background(150)

    # draw score
    fill(0)
    text("Score: " + str(score), 180, 20)
    
    # draw snake
    fill(255, 255, 255)
    for s in snake:
        rect(s[0], s[1], 10, 10)
    
    # draw apple
    fill(255, 0, 0)
    rect(apple[0], apple[1], 10, 10)

    move_snake()
    
    if snake_head_is_on_apple():
        generate_new_apple()
        score += 1
    else:
        snake.pop() # remove tail
        
    if is_snake_dead():
        background(255, 0, 0)
        fill(0)
        text("YOU LOSE!", 200, 200)
        noLoop()

def keyPressed():
    global direction
    print(key)
    if key == 'a':
        direction = "left"
    elif key == 'd':
        direction = "right"
    elif key == 'w':
        direction = "up"
    elif key == "s":
        direction = "down"
        
def is_snake_dead():
    head = snake[0]
    
    # check if snake head went outside canvas bounds
    if head[0] < 0 or head[0] > 390:
        return True
    elif head[1] < 0 or head[1] > 390:
        return True
    
    # check if snake head is touching any of the rest of snake
    rest_of_snake = snake[1:]
    for part in rest_of_snake:
        if part == head:
            return True
        
    return False
    
def move_snake():
    global score
    
    # move snake
    new_head = []
    if direction == "down":
        head = snake[0]
        new_head = [head[0], head[1]+10]
    elif direction == "up":
        head = snake[0]
        new_head = [head[0], head[1]-10]
    elif direction == "left":
        head = snake[0]
        new_head = [head[0]-10, head[1]]
    elif direction == "right":
        head = snake[0]
        new_head = [head[0]+10, head[1]]
    snake.insert(0, new_head)
    
def snake_head_is_on_apple():
    global snake, apple
    head = snake[0]
    if head[0] == apple[0] and head[1] == apple[1]:
        return True
    else:
        return False
    
def generate_new_apple():
    global apple
    apple = [int(random(2, 38))*10, int(random(2, 38))*10]

