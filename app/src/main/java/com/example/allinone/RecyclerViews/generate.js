module.exports = function(){
    var faker = require("faker");
    var _= require("lodash");
    return{
        users: _.times(1, function(n){
            return{
                id:n,
                name: faker.name.findName(),
                email: faker.internet.email(),
                username: faker.internet.userName(),
                password: faker.internet.password(),
                phonenumber: faker.phone.phoneNumber(),
                avatar: faker.internet.avatar(),
                country: faker.address.country(),
                description: faker.lorem.paragraph()
            }
        })
    }
}

//or

module.exports = () => {
    const data = { users: [] }
    // Create 1000 users
    for (let i = 0; i < 1000; i++) {
      data.users.push({ id: i, name: `user${i}` })
    }
    return data
  }