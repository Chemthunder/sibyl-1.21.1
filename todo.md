# Options to add
- Remove all armor rendering
- Remove all item rendering
- Set x and y position of `CoordinateReadout`
- Custom sky color
- Custom fog color
- Remove sky rendering

# Fixes
- Make `CoordinateReadout` not show when in F1 (unless allowed)
- ~~Make all Config options fully client-side (they aren't for some reason)~~
- Subclass the `CoordinateReadoutEvent` into a `SibylEvents` class, then have a method to register all

# If I Can I Should
- Set scaled width and height of window (making custom panoramas easier to capture)