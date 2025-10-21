# Theatrical Players - Advanced Refactoring

An advanced refactoring of Martin Fowler's Theatrical Players Kata from Chapter 1 of "Refactoring" (2nd Edition).

## What This Project Demonstrates

This project goes **beyond** Fowler's solution to demonstrate advanced domain modeling patterns:

### Improvements Over Fowler's Solution

| Aspect                | Fowler's Solution              | Our Advanced Solution                |
| --------------------- | ------------------------------ | ------------------------------------ |
| **Play Types**        | String `"tragedy"`             | Type-safe enum `PlayType.TRAGEDY`    |
| **Money**             | `int` primitives               | `Money` value object (Joda-Money)    |
| **Credits**           | `int` primitives               | `VolumeCredits` value object         |
| **Domain Objects**    | Public fields, mutable         | Private fields, immutable, validated |
| **Associations**      | String IDs                     | Object references                    |
| **Pricing Rules**     | Magic numbers                  | Named constants, explicit rules      |
| **Domain Separation** | Mixed calculation/presentation | Three separate domains               |
| **Type Safety**       | Runtime errors                 | Compile-time errors                  |

### Design Principles Applied

- **Boolean Blindness** → Rich types preserve meaning
- **Stringly-Typed Code** → Type-safe enums for play types
- **Primitive Obsession** → Money and VolumeCredits as value objects
- **MIRO** → Make illegal states unrepresentable
- **Design-Reflective** → Code structure mirrors domain concepts
- **Temporal Coupling** → Type-enforced calculation order
- **Knowledge Duplication** → Single source of truth for each concern
- **Causal Dependencies** → Explicit cause-effect relationships
- **Converging Branches** → Polymorphism instead of conditionals

## Architecture

The project is organized into three separate domains:

```
EVENT DOMAIN (what happened)
├── PlayType (enum)
├── Play
├── Performance
└── Invoice

CALCULATION DOMAIN (business rules)
├── PricingStrategy (interface)
├── TragedyPricing
├── ComedyPricing
├── VolumeCredits
├── StatementCalculator
└── StatementResult

PRESENTATION DOMAIN (formatting)
├── StatementFormatter (interface)
├── PlainTextFormatter
└── HtmlFormatter
```

## Building and Running

### Prerequisites

- Java 8 or higher
- Maven 3.6+

### Build

```bash
mvn clean compile
```

### Run Tests

```bash
mvn test
```

### Run Example

```bash
mvn exec:java -Dexec.mainClass="com.stackshala.theatricalplayers.Main"
```

## Project Structure

```
theatrical-players-advanced/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/stackshala/theatricalplayers/
│   │           ├── domain/           # Event domain (what happened)
│   │           │   ├── PlayType.java
│   │           │   ├── Play.java
│   │           │   ├── Performance.java
│   │           │   └── Invoice.java
│   │           ├── calculation/      # Calculation domain (business rules)
│   │           │   ├── PricingStrategy.java
│   │           │   ├── TragedyPricing.java
│   │           │   ├── ComedyPricing.java
│   │           │   ├── VolumeCredits.java
│   │           │   ├── StatementCalculator.java
│   │           │   └── StatementResult.java
│   │           ├── presentation/     # Presentation domain (formatting)
│   │           │   ├── StatementFormatter.java
│   │           │   ├── PlainTextFormatter.java
│   │           │   └── HtmlFormatter.java
│   │           └── Main.java         # Example usage
│   └── test/
│       └── java/
│           └── com/stackshala/theatricalplayers/
│               ├── domain/
│               ├── calculation/
│               └── presentation/
├── pom.xml
└── README.md
```

## References

- **Original Kata**: [Emily Bache's GitHub](https://github.com/emilybache/Theatrical-Players-Refactoring-Kata)
- **Fowler's Book**: "Refactoring: Improving the Design of Existing Code" (2nd Edition)
- **Blog Post**: [Coming soon on the Stackshala blog](https://www.blog.stackshala.com)

## Learning Resources

Visit [Stackshala](https://www.stackshala.com) for the complete course.

## License

MIT License - See LICENSE file for details

## Author

Created by [Stackshala](https://www.stackshala.com)
