// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)
 *
 * SECTIONS: overload-resolution, building-the-overload-candidate-set-ocs, infix-function-call
 * NUMBER: 3
 * DESCRIPTION: "Unsafe" cast doesn't work in case of property infix call
 * UNEXPECTED BEHAVIOUR
 * ISSUES: KT-36786
 */

class B(val memberVal: Any)
class C() {
    infix operator fun invoke(i: Int) { } //(1)
}

// TESTCASE NUMBER: 1
fun case1() {
    val b: B = B(C())
    b <!UNRESOLVED_REFERENCE!>memberVal<!> 1           //nok UNRESOLVED_REFERENCE
    b.memberVal.<!UNRESOLVED_REFERENCE!>invoke<!>(2)   //nok UNRESOLVED_REFERENCE
    b.<!UNRESOLVED_REFERENCE!>memberVal<!>(1)          //nok FUNCTION_EXPECTED

    b.memberVal as C

    b memberVal 1           //nok UNRESOLVED_REFERENCE !!!!
    b.memberVal.invoke(1)   //ok

    b.memberVal(1)          //nok FUNCTION_EXPECTED  !!!
    (b.memberVal)(1)        //ok

}
