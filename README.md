# Assignment 3

<div style="border:thin solid red; padding: 20px; color: red">
  <p style="margin:0; padding: 0">Your submission will be processed by source code analyzers
  for plagiarism.  You <b>must</b> work on your own assignment
  <b>individually</b>.  Sharing code is strictly prohibited.</p>
</div>

<br>


In this assignment, you will need to build an interpreter for a programming
language.  We will describe the language incrementally.

## A language for a basic calculator

Consider a language that functions as a calculator.  It supports the following:

- Numerical expression
- Variables
- Printing the result of expressions and strings to the output


Here is a sample program.

```
1 + 2
1 + 2 * 3
let pi = 3.1415
print "PI =" pi:4
let R = 10.0
print "Radius =" R:3
let area = pi * (R^2)
print "Area =" area:0 "and radius =" R:0
```

The output should be:

> ```
> 3.0
> 7.0
> PI = 3.1415
> Radius = 10.000
> Area = 31415 and radius = 10
> ```

**Expressions**

Let's look at the following features:

We can build up numerical expressions such as:

- `1 + 2`
- `1 + 2 * 3`
- `3.1415 * (10 * 10) + 1/2 * 20`

Note:

- Raw expression are printed out in the form of `> ...`.
- Also note the operator precedence.

**Variables**

Variables can be declared:

- `let x = 3.1415 * 10 * 10 + 1/2 * 20`

Variables can be used as part of the expression:

- `x + x / 2.0`

**Printing**

We can use the print statement to display expression and strings.  The results
are displayed in stdout separated by space.

Expressions can be modified by the colon construct (`pi:2`) to specify the
decimal precision the print statement should use.  So `pi:2` becomes `3.14`, and
`pi:3` is `3.142`.

## A language with mutable variables and loops

```
let pi = 3.1415
let r = 0
while (r < 10) {
  print "area =" (pi * r^2):3
  let r = r + 1
}
```

This should output:

> ```
> area = 0.000
> area = 3.142
> area = 12.566
> area = 28.273
> area = 50.264
> area = 78.538
> area = 113.094
> area = 153.934
> area = 201.056
> area = 254.462
> ```

## A language with branching

Suppose we want to find all the pairs of integers less than 100 that add up to 100.

```
let x = 1
let y = 1
while x < 100 {
  while y < 100 {
    if x + y == 100 {
      print "solution found:" x:0 y:0
    }
    let y = y + 1
  }
  let x = x + 1
}
```


# Submission

1. Complete the program with actions in `Program.g4`.
2. You are encouraged to use any helper Java classes to make the grammar as
   clean and maintainable as possible.  You must include all necessary .java
files.
3. Your project will be autograded, so make sure that your project passes all
   the unit tests.

