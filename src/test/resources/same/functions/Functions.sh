#!/bin/bash

function1() {
    echo "notUsingArgs1"
}

function function2() {
    echo "notUsingArgs2"
}

function3() {
    echo "arg = $1"
}

function4() {
    echo "args = $1, $2"
}

function function5() {
    echo "args = $1, $2, $3"
}