class City {
    String name
    int size
    boolean capital = false
    
    static def create(String n, int v, boolean e = true) {
        //return new City(name: n, size: v, capital: e)
        new City(size: v, name: n, capital: e)
    }
    
    String toString() {
        if (capital)
            return "Capital city of $name, population: ${size.toString()}"
        else
            return "City of $name, population: ${size.toString()}"
    }
}

println City.create("Brno", 400000).dump()

City c = new City(name: 'Písek', size: 25000, capital: false)

println c.dump()
c.size = 25001
println c.dump()

println c
//TASK Provide a customized toString() method to print the name and the population
assert 'City of Písek, population: 25001' == c.toString()
c.capital = true
assert 'Capital city of Písek, population: 25001' == c.toString()
println c