var express = require('express')

var port = 8219

var app = express()

var router = express.Router()

app.use(router)


app.use(express.static('./dist'))

router.get('/', function(req, res, next){
  req.url = '/index.html'
  next()
})

router.get('/blog', function(req, res, next){
  req.url = '/index.html'
  next()
})

router.get('/blog/index', function(req, res, next){
  req.url = '/index.html'
  next()
})

router.get('/blog/login', function(req, res, next){
  req.url = '/index.html'
  next()
})

router.get('/blog/aboutMe', function(req, res, next){
  req.url = '/index.html'
  next()
})


module.exports = app.listen(port, function (err) {
  if (err) {
    console.log(err)
    return
  }
  console.log('> Listening at ' + port + '\n')
})
