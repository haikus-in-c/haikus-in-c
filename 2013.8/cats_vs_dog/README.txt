Problem: Spotify Cat vs Dog
Full problem statement at: https://www.spotify.com/us/jobs/tech/catvsdog/

Essentially, there exists a game show where a bunch of cats and dogs compete and human viewers vote for one pet that they wish to keep on the show, and one that they wish to reject. 
Viewers must vote for different species for the two votes, so everybody must choose one dog and one cat depending on their preferences. 
The producers of this game show want to maximize viewership, which they assume must come when the maximum number of viewers have both of their choices satisfied. 

Determine the maximum number of viewers for each situation given number of cats, dogs, viewers, and each viewer's vote. 

Note that solutions take input from stdin in format specified in the problem
website.
Solution:

catsvsdogs.c - Incorrect. Simulates the game by ranking 'weak' pets based on
net votes (upvote - downvote) and removes weakest pets first, keeping track of
viewership numbers. Consistently returns a slightly lower value for the
answer. 

cat_dog_bipartite.c - Correct. Models the problem as a bipartite graph of
incompatible voters where the division is between cat lovers and dog lovers.
Two voters are incompatible if one wants to keep a pet the other wants to
remove - they cannot both have their vote satisfied. This solution uses a
maximum cardinality matching to determine the minimum number of incompatible
pairs and then determine the minimum number of viewers who will be unsatisfied
thereby giving the maximum number of viewers satisfied. 

test_inputs.py generates random inputs in the specified format to help compare
the two solutions. Takes an integer input for number of test cases and outputs 
them to file input.txt  
