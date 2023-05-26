JUnit Tests
ScoreRelatedTest: As it names state it the test related to the score of the user. It
test the all time Highscore of the user. The test uses the newly made file which
contains two test Highcore objects. Then using main menu high() function we
check whether the one with the highest score gets shown out of the two test that
we put in the file.

ObjectCollisionMovementTest: This contain four unit test which test the different
objects interaction with each other and their movement.
The first Unit test BasicEnemyMovementTest() checks whether our basic enemy
BaEnemy class object has automated movement or not. If it has indeed basic
movement then its update function will update its direction x-axis. From test we
can see it’s indeed true.

The Second Test is similar to that of pervious one, here we check the Trap class’s
trap objects automated movement and if it’s wobbling around x-axis or not.

The Third Test determine whether the Player is colliding with the Basic-Enemy
object of BaEnemy Class or not. If it’s colliding the Boolean Game-over of Game
will become true and as we can see in test it indeed becomes true.

Lastly, the fourth test of this determine whether player collides with the Trap
class’s Trap Object. For this purpose we make a new player and trap object with
same position to test collision. Since they have same position they will be colliding
surely. If they are indeed colliding, it’ll result in Game’s Boolean Game-over
becoming true. Which this test certainly shows

All of the testing made it realize that we do not need to worry about getting any break in our game which is result of collison and the highscore file is not gonna give any gibbrish value since we are
testing it beforehand. It really make the game bug free in long run and make it possible to run the game error free.
