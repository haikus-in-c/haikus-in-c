# game for drinking_game
# by Ian Zapolsky (8/30/13)
#
# returns a list of how many 'claps' each player will have to make
# in an argentinian drinking game

class Game:
    players = []
    forward = True
    index = 0
    breakpoint = 0
	
    def __init__(self, number_players, init_breakpoint):
        for i in range(number_players):
            self.players.append(0)
        self.breakpoint = init_breakpoint
		
    """ TODO: add 2nd param here to avoid assuming 7 """
    def clap(self, number):
        if self.factor_check(number, 7):
            return True
        if self.end_check(number, 7):
            return True
        if self.sum_check(number, 7):
            return True
        if self.palindrome(str(number)):
            return True
        return False

    """ check if number is divisible by divisor """
    def factor_check(self, number, divisor):
        return number%divisor == 0

    """ check if the one's place of number is end """
    def end_check(self, number, end):
        if end > 9 or end < 0:
            return False
        temp = str(number)
        return temp[len(temp)-1] == str(end)

    """ check if the digits of number sum to target """
    def sum_check(self, number, target):
        total = 0
        temp = str(number)
        for i in range(len(temp)):
            total += ord(temp[i]) - ord('0')
        return total == target

    """ check if number is the same forwards and backwards:
        ignores single digit numbers: see post """
    def palindrome(self, string):
        if len(string) == 1:
            return False
        return int(string[::-1]) == int(string)	
			
    """ logic to determine next player """
    def increment(self):
        if self.forward == True:
            if self.index == len(self.players)-1:
                self.index = 0
            else:
                self.index += 1
        else:
            if self.index == 0:
                self.index = len(self.players)-1
            else:
                self.index -= 1

    """ switch direction of turn cycle """
    def flip(self):
        if self.forward == True:
            self.forward == False
        else:
            self.forward == True

    """ game loop """
    def play(self):
        for i in range(1, (self.breakpoint+1)):
            if self.clap(i):
                self.players[self.index] += 1
                self.flip()
            self.increment()
        print 'results, players:'
        for i in self.players:
            print i


""" main """
g = Game(4, 1000)
g.play()
			

