'''
Created on Jan 11, 2019

@author: sisba01
'''

from fractions import Fraction
from functools import reduce
import operator

'''
    This function is used to multiply all the fractions (Rational numbers) given in Fraction list(fracs) and returns the output
    by dividing both numerator and denominator by GCD of both
'''
def product(fracs):
    # t = reduce(lambda x,y : x*y, fracs);
    # OR
    t = reduce(operator.mul, fracs);
    # complete this line with a reduce statement
    return t.numerator, t.denominator

'''
    Driver Code
'''

fracs = [];
fracs.append(Fraction(1,2));
fracs.append(Fraction(3,4));
fracs.append(Fraction(5,6));
fracs.append(Fraction(7,8));

numerator, denominator = product(fracs);

print("Numeratro: %d Denominator: %d" % (numerator, denominator));

