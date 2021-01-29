f= open('gameReference.txt', 'r')
no = 'Not a valid input'
end = ('-'*22, 'END', '-'*22)
winPoints = 0
losePoints = 0
noWin= False


listOfLines = list()        
with open ("gameReference.txt",encoding="utf-8") as myfile:
    for line in myfile:
        listOfLines.append(line.strip())

#BEGIN DEFINING FUNCS
#Opening Screen: DONE
print('-'*20, 'SECRET SPY', '-'*20)
def startGame():
    print(' '*17, 'PRESS 1 TO BEGIN')
    enterGame = str(input())
    if enterGame == '1':
        openFolder(0)
    else:
        startGame()

#yes and no prompt: open folder
def openFolder(value):
    if value == 0:
        for i in range(0,9):
            print(listOfLines[i])
        
        answer = input()
        
        if answer == 'yes':
            pass
        elif answer == 'no':
            for i in range(9,12):
                print(listOfLines[i])
            openFolder(1)
        else:
            print(no)
            openFolder(0)
    if value == 1:
        answer = input()
        if answer == 'yes':
            pass
        elif answer == 'no':
            print(listOfLines[13])
        else:
            print(no)
            openFolder(1)

#Begin actually doing things: DONE
def campaignOffice():
    for i in range(15,24):
        print(listOfLines[i])
    
    response = str(input())
    response = response.strip()
    response = response.lower()
    if response == 'be skeptical':
        global winPoints
        winPoints+=1
    elif response == 'accept response':
        global losePoints
        losePoints +=1
    else:
        print(no)
        campaignOffice()

#the next universal function
def spreadsheet():
    for i in range(25,31):
        print(listOfLines[i])
    print('(Inspect/Ignore)')
    response = str(input())
    response = response.strip()
    response = response.lower()
    if response == 'inspect':
        global winPoints
        winPoints+=1
        print(listOfLines[32])
    elif response == 'ignore':
        global losePoints
        losePoints +=1
        print(listOfLines[34])
    else:
        print(no)
        spreadsheet()

def telephone():
    for i in range(36,41):
        print(listOfLines[i])
    response = str(input())
    response = response.strip()
    response = response.lower()
    if response == 'yes':
        global winPoints
        winPoints+=1
        visit()
    elif response == 'no':
        print(listOfLines[41])
        global losePoints
        losePoints +=1
    else:
        print(no)
        telephone()
        
def visit():
    for i in range(43,47):
        print(listOfLines[i])
    response = str(input())
    response = response.strip()
    response = response.lower()
    if response == 'yes':
        global winPoints
        winPoints+=1
        bribe()
    elif response == 'no':
        for i in range(46,49):
            print(listOfLines[i])
        global losePoints
        losePoints +=1
    else:
        print(no)
        visit()

def bribe():
    for i in range(49,57):
        print(listOfLines[i])
    response = str(input())
    response = response.strip()
    response = response.lower()
    if response == 'yes':
        for i in range(57,60):
            print(listOfLines[i])
        global winPoints
        winPoints+=1
    elif response == 'no':
        for i in range(60,64):
            print(listOfLines[i])
        global losePoints
        losePoints +=1
    else:
        print(no)
        bribe()

def headHome():
    for i in range(64,68):
        print(listOfLines[i])
    response = str(input())
    response = response.strip()
    response = response.lower()
    if response == 'investigate':
        for i in range(68,70):
            print(listOfLines[i])
        global winPoints
        winPoints+=1
        offTheCase()
    elif response == 'go in as normal':
        print(listOfLines[70])
        print('GAME OVER')
        global losePoints
        losePoints +=1
    else:
        print(no)
        headHome()
       
def offTheCase():
    for i in range(72,85):
        print(listOfLines[i])
    response = str(input())
    response = response.strip()
    response = response.lower()
    if response == 'keep going':
        global winPoints
        winPoints+=1
        print(listOfLines[86])
        anotherCall()
    elif response == 'accept':
        global losePoints
        losePoints +=1
        print('Well, looks like you\'re off the case')
        print('GAME OVER')
    else:
        print(no)
        offTheCase()
        
def anotherCall():
    for i in range(87,89):
        print(listOfLines[i])
    response = str(input())
    response = response.strip()
    response = response.lower()
    if response == 'visit his home':
        global winPoints
        winPoints+=1
        mask()
        goingIn()
    elif response == 'wait until later':
        global losePoints
        losePoints +=1
        print('Your boss comes in to give you a new case. He realizes you\'ve still been working on the case. He fires you.')
        print('GAME OVER')
    else:
        print(no)
        anotherCall()
        
def mask():
    for i in range(90,92):
        print(listOfLines[i])
    response = str(input())
    response = response.strip()
    response = response.lower()
    if response == 'pick it up':
        print(listOfLines[93],'\n')
        global winPoints
        winPoints+=1
    elif response == 'leave it':
        global losePoints
        losePoints +=1
    else:
        print(no)
        mask()

def goingIn():
    for i in range(95,99):
        print(listOfLines[i])
    response = str(input())
    response = response.strip()
    response = response.lower()
    if response == 'go in':
        print(listOfLines[100])
        global winPoints
        winPoints+=1
        laptop()
    elif response == 'leave':
        print(listOfLines[102])
        global losePoints
        losePoints +=1
        print('You leave, but without barely any evidence. Because of this, there is no way you can solve the case.')
        print('GAME OVER')
    else:
        print(no)
        goingIn()
        
def laptop():
    for i in range(104,106):
        print(listOfLines[i])
    response = str(input())
    response = response.strip()
    response = response.lower()
    if response == 'yes':
        print(listOfLines[107])
        takeLaptop()
        global winPoints
        winPoints+=1
    elif response == 'no':
        global losePoints
        losePoints +=1
    else:
        print(no)
        laptop()
        
def takeLaptop():
    for i in range(108,111):
        print(listOfLines[i])
    response = str(input())
    response = response.strip()
    response = response.lower()
    if response == 'no':
        global winPoints
        winPoints+=1
        for i in range(112,114):
            print(listOfLines[i])
        print("You win.")
    elif response == 'yes':
        global losePoints
        losePoints +=1
        for i in range(115,117):
            print(listOfLines[i])
    else:
        print(no)
        takeLaptop()

#ACTUALLY RUNS GAME
startGame()
campaignOffice()
spreadsheet()
telephone()
headHome()
print('Win Points:', winPoints)
print('Lose Points:',losePoints)
f.close()