#!/bin/bash

runner=$1

if test -f Main.py
then
	runner="python3.7 Main.py"
elif test -f Main.java
then
	echo "Attempting to compile..."
	javac *.java
	runner="java Main"
fi

score=0
error=0

for value in {1..13}
do
	echo "Running ${value}.code"
	timeout 5 ${runner} Correct/${value}.code > Correct/${value}.student
	echo "Comparing with ${value}.expected"
	#Check for correct print
	if cmp -s "Correct/${value}.expected" "Correct/${value}.student"; then
		echo "Print looks good"
		score=$(($score + 1))
	else
		echo "Student output and expected output are different"
	fi
done

echo ""

echo "Running error cases:"
echo ""
echo "Running 1.error:"
timeout 5 ${runner} Error/1.code Error/1.data
read -n 1 -p "Error is ':' in file. Error message related to that? (y/n)" mainmenuinput
if [ $mainmenuinput = "y" ]; then
	error=$(($error + 1))
fi

echo ""

echo "Running error cases:"
echo ""
echo "Running 2.error:"
timeout 5 ${runner} Error/2.code Error/2.data
read -n 1 -p "Error is '?' in file. Error message related to that? (y/n)" mainmenuinput
if [ $mainmenuinput = "y" ]; then
	error=$(($error + 1))
fi

echo ""

echo "Running error cases:"
echo ""
echo "Running 3.error:"
timeout 5 ${runner} Error/3.code Error/3.data
read -n 1 -p "Error is '$' in file. Error message related to that? (y/n)" mainmenuinput
if [ $mainmenuinput = "y" ]; then
	error=$(($error + 1))
fi

echo ""

echo "Running error cases:"
echo ""
echo "Running 4.error:"
timeout 5 ${runner} Error/4.code Error/4.data
read -n 1 -p "Error is constant too large in file. Error message related to that? (y/n)" mainmenuinput
if [ $mainmenuinput = "y" ]; then
	error=$(($error + 1))
fi

echo ""

echo "Correct cases score out of 13:"
echo $score
echo "Error cases score out of 4:"
echo $error


echo Done!
