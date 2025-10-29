# TODO List for QuotesApp Implementation

- [x] Update activity_main.xml to include TextView for quote display, Next Quote button, and Share button with proper IDs and positioning.
- [x] Update MainActivity.java to:
  - Declare and initialize a String array with at least 10 inspirational quotes.
  - Declare variables for TextView and Buttons.
  - Bind UI components in onCreate().
  - Implement displayRandomQuote() method to select and display a random quote.
  - Set OnClickListener for nextButton to call displayRandomQuote().
  - Set OnClickListener for shareButton to create and start an implicit intent for sharing the current quote.
- [x] Test the app functionality (build and run to ensure quotes display randomly and sharing works).
- [x] Update layout to match William Faulkner quote card design with header, background image, card container, yellow title box, quote text, and share button.
- [x] Add colors and strings resources for the new design.
- [x] Change quote list to ArrayList and include the Faulkner quote as first item.
- [x] Remove nextButton from layout and code as per new design.
- [x] Add NEXT QUOTE button back to allow switching between different quotes including non-Faulkner ones.
- [x] Update header text in yellow box to "QUOTE TODAY FOR YOU".
- [x] Change nextButton text color to white.
- [x] Center align "QUOTE TODAY FOR YOU" text and change font size to 16sp.
