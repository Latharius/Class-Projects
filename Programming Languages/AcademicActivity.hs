multiply2 x y = x * y

multiply3 x y z = x * y * z

first_a n = [n | n <- [1..n], (n `mod` 6 == 0 || n `mod` 11 == 0)]

isMult6Or11 n = (n `mod` 6 == 0 || n `mod` 11 == 0)
first_b n = [n | n <- [1..n], isMult6Or11 n]

second_a n = [n | n <- [1..n], reverse n == n, head n == 3]

isPalindromeThatStartsWithDigit3 n = (reverse n == n && head n == 3)
second_b n = [n | n <- [1..n], isPalindromeThatStartsWithDigit3 n]