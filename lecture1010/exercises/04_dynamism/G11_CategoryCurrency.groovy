class Money {
    Integer amount
    String currency

    Money plus(Money other) {
        if (this.currency != other.currency) throw new IllegalArgumentException('Cannot add different currencies');
        new Money(amount: this.amount + other.amount, currency: this.currency)
    }

    public String toString() {
        "${amount} ${currency}"
    }
}

class MoneyCategory {
//TASK Define methods of the MoneyCategory class so that the code below passes
    public static Money getEur(Integer amount) { // now its a property, not a method
        new Money(amount: amount, currency: "EUR")
    }
    
    public static Money getUsd(Integer amount) {
        new Money(amount: amount, currency: "USD")
    }
}

def m = new Money(amount: 10, currency: "CZK")
println m

use(MoneyCategory) {
    println 10.eur
    println 10.eur + 20.eur
    println 10.usd + 20.usd
}