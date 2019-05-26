# Robotic Oil Cleaner

A Java based web service that navigates an imaginary robotic cleaner through an oil spill in the sea. This is based on the following gist https://gist.github.com/alirussell/9a519e07128b7eafcb50

## Build instructions

Navigate to the project directory and run:

`mvn clean install`

`mvn spring-boot:run`

The logs should verify the application has started.

## Input

The api accepts a JSON payload through a `POST` request to `/rest/robot/clean` with the format described below.

Example:

```json
{
  "areaSize" : [5, 5],
  "startingPosition" : [1, 2],
  "oilPatches" : [
    [1, 0],
    [2, 2],
    [2, 3]
  ],
  "navigationInstructions" : "NNESEESWNWW"
}
```

## Output

The following JSON message is returned.

Example (matching the input above):

```json
{
  "finalPosition" : [1, 3],
  "oilPatchesCleaned" : 1
}
```
Where `finalPosition` are the final coordinates of the cleaner and `oilPatchesCleaned` is the number of cleaned patches.


