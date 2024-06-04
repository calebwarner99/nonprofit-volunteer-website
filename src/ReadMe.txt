The following is the written documentation for the TechPointX SOS Challenge Nonprofits 03 Project.



--------------------------------------------------------------------------------------------------------------
Acknowledgement:
--------------------------------------------------------------------------------------------------------------
Dr. Jose Annunziato - Created online tutorials showing viewers how to create and set up a Heroku and Spring-Boot
project as well as how to add the ClearDB MySQL database to the project.

Shelby Benton - A large contributor to the html and css code located in src\main\webapp.

Maggie Reeves - A large contributor to the html and css code located in src\main\webapp.

Caleb Warner - A large contributor to the java code located in src\main\java\com\example\TPXProj.

Josh Lane - The Pro Squad coach for our team. Contributed great tips and pointers for our team.


Heroku - Gave our team a free platform to host our website.

Spring-Boot - Gave our team a free framework to handle website requests.

ClearDB MySQL Database - Gave our team a free relational database to store persistent data.

Stack Overflow - Gave our team several small code snippets and examples.


TechPointX SOS Challenge SME's and Judges - Thank you for looking over our project! We worked very hard on it!



--------------------------------------------------------------------------------------------------------------
Website URL:
--------------------------------------------------------------------------------------------------------------
The website project can be accessed at: https://tpx-nonprofits-03.herokuapp.com/index.html



--------------------------------------------------------------------------------------------------------------
Code Documentation:
--------------------------------------------------------------------------------------------------------------
The website java documentation can be accessed at: https://tpx-nonprofits-03.herokuapp.com/javadoc/index.html




--------------------------------------------------------------------------------------------------------------
Getting Setup:
--------------------------------------------------------------------------------------------------------------
In order to work on the project remotely, you must first have git on your computer.
This can be done at the git download website.

Make sure git is working with:
git --version

Now you must download the Heroku CLI.
This can be done on Heroku's CLI download page.
Use downloadable file for Windows,
use HomeBrew for Mac (may have to download XCode and HomeBrew for Mac)

Within your Heroku CLI, type:
heroku login -i    (put in credentials, may have to create an account)
cd [project directory]
git init
git config --global user.email [your email]
git config --global user.name [your name]
git add .
git commit -am "Initial commit"
git remote add heroku https://git.heroku.com/tpx-nonprofits-03.git
git pull heroku master


You should be all set!

If you want to run the project locally to your computer for testing, you will need to have the 
latest JDK and Maven installed on your computer.




To modify the project, follow these steps:
git pull heroku master       (before you start modifying)
(make your modifications)
git add .
git commit -am "Commit message; what you did/changed"
git pull heroku master       (in case something changed while you were working)
git push heroku master


Now you changes are online and live!




Lastly, if you'd like to see what is in the database and alter the database, you will need to download
the MySQL Workbench from the internet.






--------------------------------------------------------------------------------------------------------------
Common Problems/Mistakes:
--------------------------------------------------------------------------------------------------------------
Here are a few tips and tricks we discovered to avoid frustrating problems.


If git is having problems uploading, it is often due to a misalignment between your local repository and
the online, public repository. Try running these commands:
git add .
git commit -am [commit message]
git pull heroku master
git push heroku master

This helps solve issues with git most of the time.



Another issue is our website crashing when we try to run it.
If it is not an issue with a fatal error in the code, it is often due to the fact that we are using
a free version of the ClearDB MySQL Database.

It gives each user a maximum of 10 connections. And considering each connection takes time to expire
once it ends and each connection takes 2 or more for management and queries, this maximum can be
surpassed fairly easily.

In this case, simply close any local programs you have connecting to the database and wait a minute
or two for the sessions to expire. The website should start right up.








--------------------------------------------------------------------------------------------------------------
General Program Workflow:
--------------------------------------------------------------------------------------------------------------
The general program workflow is as follows:

-Any internet request not needing special processing is retrieved from src/main/webapp directly by Spring-Boot.

-Any request needing special processing is received by DatabaseService.

--DatabaseService uses either NonprofitProcessor or VolunteerProcessor to handle the request.

--NonprofitProcessor uses NonprofitParser to translate data and FileReader to read any needed files.
	It also uses the repository to store a submitted nonprofit.

---NonprofitParser converts html form Strings to Nonprofit objects, database objects to Nonprofit objects,
	and Nonprofit objects to database objects, depending on the need.

---FileReader creates and attempts to read a file and deliver it to its caller. If that fails, it attempts to deliver
	a file-not-found html page. If that fails, it delivers plain text.

--VolunteerProcessor uses VolunteerParser to translate data. Then it retrieves all the nonprofits from
	the repository and ranks them. Then it uses FileReader to read any needed files. Lastly, it uses 
	NonprofitInserter to insert the top 4 nonprofits into the html file.

---VolunteerParser converts html form Strings to Volunteer objects.

---NonprofitInserter inserts the top 4 nonprofits into the given html file by replacing the predefined place holders
	with the values derived from the nonprofits.



--------------------------------------------------------------------------------------------------------------
Important Notes:
--------------------------------------------------------------------------------------------------------------
The following is important notes regarding how data is stored and interpreted in the program.

Within the ClearDB MySQL Database, nonprofits are stored as a row with Strings for each field except
for location which is an Integer.


An example is:
name,		website,	phone,		email,			needs, activities, skills, commitments, location
"Nonprofit", "nonprofit.com", "123-456-7890", "nonprofit%40email.com", "1", "10001000", "10010", "100", 	"3"





An important note here:
Most of the contact info is straightforward and stored as literals, but the database makes automatic conversion
of special characters (like '@') to a '%' followed by the hex representation of their ASCII value (like 40).

NonprofitParser cleans this up (with cleanString) and puts the actual values back where they belong upon retrieving those
Strings from the database.




Another note:
needs, activities, skills, commitments, and location are all indexed. This means that the Strings they contain act as
pseudo-binary numbers in which the further away from the right it is, the higher its index value.

For example, Needs would technically be "001" which translates to it having a need of index 0.
The OutputMapper would map this to "Volunteering"

As another example, Activities would have indices consisting of 3, and 7.
The OutputMapper would map these to "Civic Engagement" and "Environmental"

As you can see, with this method of storing the various values results in a way to store as many (all 1's) or few (all 0's)
of each field for each nonprofit.



Lastly, Location simply stores its index value. (3 would map to "Eastern" Indiana)



With that said, the Nonprofit object stores an ArrayList of integer indices instead of a pseudo-binary String.
This makes handling and manipulating them much easier, and they are translated back to their respective String
if they need to be stored (using deparseNonprofit within NonprofitParser).


The Volunteer object also uses indices but it does not have to be stored in the database so it does not deal
with these difficulties.






When the website receives a HTTP 'POST' request for either Nonprofit submission or Volunteer search, it retrieves a String
in the form of "name=value&name=value&name=value".

For example, the following is a Volunteer wanting to find all Nonprofits which are looking for volunteers and are
located in Central Indiana:

"contribution=0&location=0"

You can see that we can simply separate this string based on the placement of the '&'s and '='s and the values are
already indexed for us, so we can simply store them in a Volunteer object.


The same applies to Nonprofits, although the name, website, phone, and email fields will always appear, even
if empty. Whereas the indexed fields will only appear if the user selected them (ie they will never be empty)





The ranking system of nonprofits based on volunteers works in the following order:
1. location
2. need
3. commitment
4. activities
5. skills


The program uses a system of ranking that makes individual component worth more than all components beneath it combined.

For example, skills are worth 1 point, and there are 11 skills.
So the maximum amount that skills can affect the score is to increase it by 11; therefore, each activity is worth 12 points.
With this system, each field will affect the scoring more than any beneath it, and the ones beneath that field will determine
the highest ranking nonprofit in the case of a tie.

Points:
location: 720
need: 360
commitment: 180
activity: 12     (there are 14 possible)
skills: 1        (there are 11 possible)



These scores are added to a priority queue which takes the top four (if all are tied it takes an arbitrary four).






--------------------------------------------------------------------------------------------------------------
Security Considerations:
--------------------------------------------------------------------------------------------------------------
This project was created with security in mind.

The database url, username, hostname, schema, and password were stored as environment variables inside Heroku
so that they are not in the source files for the general public. Hard coding these variables
could release this sensitive information to the public.

We were also sure to have our website have a "https://" prefix so that we can rest assured our http requests
are being encrypted in transport.







