## Work In progress

## Synopsis

This java/spring project that provides a **proxy** that sends all HTTP headers for basic authentication. This is useful when you need to pass the authentication headers and cookies.

## Project's name

In Greek mythology, ["Tiresias"](https://en.wikipedia.org/wiki/Tiresias) (/taɪˈriːsiəs/; Greek: Τειρεσίας, Teiresias) was a blind prophet of Apollo in Thebes.
And a ["Prophet"](https://en.wikipedia.org/wiki/Prophet) refers to a person who serves as an intermediary between humanity and divinity.

## Code Example

This is called from outside the Application. Then use it from your method that will need authentication in the following way.

```java
 @ResponseBody
  @RequestMapping(method = RequestMethod.GET, value = "/entity/{entityId}",
                  produces = MediaType.APPLICATION_JSON_VALUE)
  public MyEntity getMyEntity(
      @PathVariable("entityId") final String entityId,
      HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    return handler.callAndHandleHttp(request, response, new RequestWithEntity<MyEntity>() {
      public ResponseEntity<MyEntity> call(HttpEntity<Void> requestEntity) {
        ResponseEntity<MyEntity> res = service.getMyEntityBasedOnId(entityId, requestEntity);
        return res;
      }
    });
  }
```
From the application itself, you will also need your own DTOs. In my case just to show how it works I added some Pojos in the DTOs package.

## Tests

There are some simple unit and integration tests with code examples.

## Work pending:

Refactoring and separating concerns. Create 2 maven projects, one jar and one war.

The jar project will be the library itself, and the war maven project will be the example on how to use it.
