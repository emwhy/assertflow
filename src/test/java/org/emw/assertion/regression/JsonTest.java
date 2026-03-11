package org.emw.assertion.regression;

import org.emw.assertion.json.JsonAssertor;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class JsonTest implements JsonAssertor {
    @Test
    public void testJson() {
        final String testJson = """
                {
                  "university_system": {
                    "name": "Global Tech Institute",
                    "founded_year": 1985,
                    "end_year": null,
                    "open": true,
                    "campuses": [
                      {
                        "location": "North Campus",
                        "departments": [
                          {
                            "name": "Computer Science",
                            "head": {
                              "name": "Dr. Sarah Chen",
                              "contact": {
                                "email": "s.chen@gti.edu",
                                "office": "Bldg A, 402"
                              }
                            },
                            "courses": [
                              {
                                "code": "CS101",
                                "title": "Intro to Algorithms",
                                "enrollment": {
                                  "total": 120,
                                  "students": [
                                    {
                                      "id": "ST-001",
                                      "name": "Alice Smith",
                                      "grades": [
                                        { "exam": "Midterm", "score": 88 },
                                        { "exam": "Final", "score": 92 }
                                      ]
                                    },
                                    {
                                      "id": "ST-002",
                                      "name": "Bob Johnson",
                                      "grades": [
                                        { "exam": "Midterm", "score": 75 },
                                        { "exam": "Final", "score": 81 }
                                      ]
                                    }
                                  ]
                                }
                              }
                            ],
                            "research_labs": [
                              {
                                "lab_name": "AI Foundations",
                                "projects": [
                                  {
                                    "title": "Neural Mapping",
                                    "funding": {
                                      "source": "Tech Corp",
                                      "amount": 500000,
                                      "currency": "USD"
                                    }
                                  }
                                ]
                              }
                            ]
                          }
                        ]
                      },
                      {
                        "location": "South Campus",
                        "specialization": "Medical Sciences",
                        "facilities": [
                          {
                            "type": "Hospital",
                            "capacity": 250,
                            "wards": ["ICU", "Pediatrics", "Cardiology"]
                          },
                          {
                            "type": "Library",
                            "access": "24/7",
                            "floors": [
                              { "level": 1, "section": "Reference" },
                              { "level": 2, "section": "Quiet Study" }
                            ]
                          }
                        ]
                      }
                    ],
                    "global_stats": {
                      "total_students": 15400,
                      "international_ratio": 0.22,
                      "affiliations": ["Global Edu Group", "Research Alliance"]
                    }
                  }
                }
                
                """;
        assertJson(testJson).expect(json -> {
            json.node("/university_system/name").to.caseInsensitively.be("global Tech Institute");
            json.node("/university_system/name").to.be("Global Tech Institute");
            json.node("/university_system/founded_year").to.be(1985);
            json.node("/university_system/founded_year").to.not.be(198);
            json.node("/university_system/global_stats/international_ratio").to.be(0.22);
            json.node("/university_system/founded_year").to.not.be.stringType();
            json.node("/university_system/end_year").to.be.nullValue();
            json.node("/university_system").to.not.be.stringType();
            json.node("/university_system/name", nameNode -> {
                nameNode.to.not.be.string.empty();
                nameNode.to.be.string.startWith("Global");
                nameNode.to.be.string.endWith("Institute");
                nameNode.to.be.string.contain("Tech");
                nameNode.to.caseInsensitively.be.string.startWith("GLOBAL");
                nameNode.to.caseInsensitively.be.string.endWith("INSTITUTE");
                nameNode.to.caseInsensitively.be.string.contain("TECH");
            });
            json.node("/university_system/founded_year").to.not.be.stringType();
            json.node("/university_system/founded_year").to.be.numberType();
            json.node("/university_system/founded_year").to.be.number.greaterThan(1970);
            json.node("/university_system/founded_year").to.be.number.lessThan(2000);
            json.node("/university_system/founded_year").to.be.number.between(1000, 2000);
            json.node("/university_system/founded_year").to.not.be.booleanType();
            json.node("/university_system/open").to.be.booleanType();
            json.node("/university_system/open").to.be.bool.trueValue();
            json.node("/university_system/global_stats/international_ratio").to.be(0.22);
            json.node("/university_system").to.excluding("/campuses", "/global_stats/total_students", "end_year").be("""
                {
                    "name": "Global Tech Institute",
                    "founded_year": 1985,
                    "open": true,
                    "global_stats": {
                      "total_students": 154000000,
                      "international_ratio": 0.22,
                      "affiliations": ["Global Edu Group", "Research Alliance"]
                    }
                }
                """);
            json.to.findJson("""
                          {
                            "access": "24/7",
                            "type": "Library",
                            "floors": [
                              { "level": 1, "section": "Reference" },
                              { "level": 2, "section": "Quiet Study" }
                            ]
                          }
                    """);
            json.to.excluding("/access").findJson("""
                          {
                            "access": "24/7999",
                            "type": "Library",
                            "floors": [
                              { "level": 1, "section": "Reference" },
                              { "level": 2, "section": "Quiet Study" }
                            ]
                          }
                    """);
        });
    }

    @Test
    public void testJsonArray() {
        final String testJson = """
                  [
                    {
                      "id": "ST-001",
                      "name": "Alice Smith",
                      "rooms": ["room1-1", "room1-2", "room1-3"],
                      "numbers": [0.4, 4, 8],
                      "grades": [
                        { "exam": "Midterm", "score": 88 },
                        { "exam": "Final", "score": 92 }
                      ]
                    },
                    {
                      "id": "ST-002",
                      "name": "Bob Johnson",
                      "rooms": ["room2-1", "room2-2", "room2-3"],
                      "numbers": [4, 48, .8],
                      "grades": [
                        { "exam": "Midterm", "score": 75 },
                        { "exam": "Final", "score": 81 }
                      ]
                    }
                  ]
                """;
        assertJsonArray(testJson).expect(jsonNodes -> {
            jsonNodes.first().node("/id").to.be.string.startWith("ST-001");
            jsonNodes.last().node("/id").to.be.string.startWith("ST-002");
            jsonNodes.last().nodes("/grades").first().node("/score").to.be.number.lessThan(88);
            jsonNodes.to.be(testJson);
            jsonNodes.to.not.be("['test']");
            jsonNodes.to.findJson("""
                [
                    { "exam": "Midterm", "score": 75 },
                    { "exam": "Final", "score": 81 }
                ]
                """);
            jsonNodes.to.not.findJson("""
                [
                    { "exam": "Midterm", "score": 750 },
                    { "exam": "Final", "score": 81 }
                ]
                """);
            jsonNodes.to.caseInsensitively.findJson("""
                [
                    { "exam": "midterm", "score": 75 },
                    { "exam": "final", "score": 81 }
                ]
                """);
            jsonNodes.to.excluding("/rooms", "/numbers").allMatch("""
                    {
                      "id": "ST-001",
                      "name": "Alice Smith",
                      "grades": [
                        { "exam": "Midterm", "score": 88 },
                        { "exam": "Final", "score": 92 }
                      ]
                    }
                    """, """
                    {
                      "id": "ST-002",
                      "name": "Bob Johnson",
                      "grades": [
                        { "exam": "Midterm", "score": 75 },
                        { "exam": "Final", "score": 81 }
                      ]
                    }

                    """);
            jsonNodes.first().nodes("/rooms").to.allMatch("room1-1", "room1-2", "room1-3");
            jsonNodes.first().nodes("/rooms").to.caseInsensitively.allMatch("ROOM1-1", "ROOM1-2", "ROOM1-3");
            jsonNodes.first().nodes("/rooms").to.inAnyOrder.allMatch("room1-2", "room1-3", "room1-1");
            jsonNodes.first().nodes("/numbers").to.allMatch(.4, 4, 8);
            jsonNodes.to.inAnyOrder.excluding("/rooms", "/numbers").allMatch("""
                    {
                      "id": "ST-002",
                      "name": "Bob Johnson",
                      "grades": [
                        { "exam": "Midterm", "score": 75 },
                        { "exam": "Final", "score": 81 }
                      ]
                    }
                    """, """
                    {
                      "id": "ST-001",
                      "name": "Alice Smith",
                      "grades": [
                        { "exam": "Midterm", "score": 88 },
                        { "exam": "Final", "score": 92 }
                      ]
                    }
                    """);
            jsonNodes.to.excluding("/rooms", "/numbers").not.allMatch("""
                    {
                      "id": "ST-002",
                      "name": "Bob Johnson",
                      "grades": [
                        { "exam": "Midterm", "score": 75 },
                        { "exam": "Final", "score": 81 }
                      ]
                    }
                    """, """
                    {
                      "id": "ST-001",
                      "name": "Alice Smith",
                      "grades": [
                        { "exam": "Midterm", "score": 88 },
                        { "exam": "Final", "score": 92 }
                      ]
                    }
                    """);
            jsonNodes.to.excluding("/rooms", "/numbers").contain("""
                    {
                      "id": "ST-002",
                      "name": "Bob Johnson",
                      "grades": [
                        { "exam": "Midterm", "score": 75 },
                        { "exam": "Final", "score": 81 }
                      ]
                    }
                    """);
            jsonNodes.to.excluding("/rooms", "/numbers").contain("""
                    {
                      "id": "ST-002",
                      "name": "Bob Johnson",
                      "grades": [
                        { "exam": "Midterm", "score": 75 },
                        { "exam": "Final", "score": 81 }
                      ]
                    }
                    """, """
                    {
                      "id": "ST-001",
                      "name": "Alice Smith",
                      "grades": [
                        { "exam": "Midterm", "score": 88 },
                        { "exam": "Final", "score": 92 }
                      ]
                    }
                    """);
            jsonNodes.to.excluding("/rooms", "/numbers").not.contain("""
                    {
                      "id": "ST-002",
                      "name": "Bob Johnson Test",
                      "grades": [
                        { "exam": "Midterm", "score": 75 },
                        { "exam": "Final", "score": 81 }
                      ]
                    }
                    """, """
                    {
                      "id": "ST-001",
                      "name": "Alice Smith",
                      "grades": [
                        { "exam": "Midterm", "score": 88 },
                        { "exam": "Final", "score": 92 }
                      ]
                    }
                    """);
            jsonNodes.first().nodes("/rooms").to.contain("room1-2", "room1-3");
            jsonNodes.to.be.sizeOf(2);
            jsonNodes.to.not.be.empty();
        });
        assertJsonArray("[]").expect(jsonNodes -> jsonNodes.to.be.empty());
    }

    @Test
    public void testJsonDate() {
        final String testJson = """
                {
                  "date_formats": [
                    {
                      "formatter": "DateTimeFormatter.BASIC_ISO_DATE",
                      "pattern": "yyyyMMdd",
                      "example": "20260309"
                    },
                    {
                      "formatter": "DateTimeFormatter.ISO_DATE",
                      "pattern": "yyyy-MM-dd+HH:mm",
                      "example": "2026-03-09-04:00"
                    },
                    {
                      "formatter": "DateTimeFormatter.ISO_OFFSET_DATE",
                      "pattern": "yyyy-MM-dd+HH:mm",
                      "example": "2026-03-09-04:00"
                    },
                    {
                      "formatter": "DateTimeFormatter.ISO_LOCAL_DATE",
                      "pattern": "yyyy-MM-dd",
                      "example": "2026-03-09"
                    },
                    {
                      "formatter": "DateTimeFormatter.ISO_ORDINAL_DATE",
                      "pattern": "yyyy-DDD",
                      "example": "2026-068"
                    },
                    {
                      "formatter": "DateTimeFormatter.ISO_WEEK_DATE",
                      "pattern": "YYYY-w-e",
                      "example": "2026-W11-1"
                    },
                    {
                      "formatter": "DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)",
                      "pattern": "EEEE, MMMM d, yyyy",
                      "example": "Monday, March 9, 2026"
                    },
                    {
                      "formatter": "DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)",
                      "pattern": "MMMM d, yyyy",
                      "example": "March 9, 2026"
                    },
                    {
                      "formatter": "DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)",
                      "pattern": "MMM d, yyyy",
                      "example": "Mar 9, 2026"
                    },
                    {
                      "formatter": "DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)",
                      "pattern": "M/d/yy",
                      "example": "3/9/26"
                    },
                    {
                      "formatter": "DateTimeFormatter.ofPattern(\\"MM/dd/yyyy\\")",
                      "pattern": "MM/dd/yyyy",
                      "example": "03/09/2026"
                    },
                    {
                      "formatter": "DateTimeFormatter.ofPattern(\\"M/d/yyyy\\")",
                      "pattern": "M/d/yyyy",
                      "example": "3/9/2026"
                    }
                  ]
                }
                
                """;
        assertJson(testJson).expect(json -> {
            json.nodes("/date_formats").forEach(node -> {
                node.node("/example").to.be.dateType();
                node.node("/example").to.be.date.of(LocalDate.of(2026, 3, 9));
            });
        });
    }

    @Test
    public void testJsonDateTime() {
        final String testJson = """
                {
                  "datetime_formats": [
                    {
                      "formatter": "DateTimeFormatter.ISO_LOCAL_DATE_TIME",
                      "pattern": "yyyy-MM-dd'T'HH:mm:ss",
                      "example": "2026-03-09T13:24:45"
                    },
                    {
                      "formatter": "DateTimeFormatter.ISO_OFFSET_DATE_TIME",
                      "pattern": "yyyy-MM-dd'T'HH:mm:ssXXX",
                      "example": "2026-03-09T13:24:45-04:00"
                    },
                    {
                      "formatter": "DateTimeFormatter.ISO_ZONED_DATE_TIME",
                      "pattern": "yyyy-MM-dd'T'HH:mm:ssXXX'['VV']'",
                      "example": "2026-03-09T13:24:45-04:00[America/New_York]"
                    },
                    {
                      "formatter": "DateTimeFormatter.ISO_DATE_TIME",
                      "pattern": "ISO-8601 with optional offset/zone",
                      "example": "2026-03-09T13:24:45.123-04:00"
                    },
                    {
                      "formatter": "DateTimeFormatter.RFC_1123_DATE_TIME",
                      "pattern": "EEE, d MMM yyyy HH:mm:ss z",
                      "example": "Mon, 9 Mar 2026 13:24:45 GMT"
                    },
                    {
                      "formatter": "DateTimeFormatter.ofPattern(\\"MM/dd/yyyy HH:mm:ss\\")",
                      "pattern": "MM/dd/yyyy HH:mm:ss",
                      "example": "03/09/2026 13:24:45"
                    },
                    {
                      "formatter": "DateTimeFormatter.ofPattern(\\"M/d/yyyy H:mm:ss\\")",
                      "pattern": "M/d/yyyy H:mm:ss",
                      "example": "3/9/2026 13:24:45"
                    },
                    {
                      "formatter": "DateTimeFormatter.ofPattern(\\"yyyy-MM-dd HH:mm:ss\\")",
                      "pattern": "yyyy-MM-dd HH:mm:ss",
                      "example": "2026-03-09 13:24:45"
                    }
                  ]
                }
                """;

        assertJson(testJson).expect(json -> {
            json.nodes("/datetime_formats").stream().forEach(node -> {
                node.node("/example").to.be.dateTimeType();
                node.node("/example").to.be.dateTime.sameOrAfter(LocalDateTime.of(2026, 3, 9, 13, 24, 45));
                node.node("/example").to.be.dateTime.before(LocalDateTime.of(2026, 3, 9, 13, 25));
            });
        });

    }

    @Test
    public void testJsonTime() {
        final String testJson = """
                {
                  "time_formats": [
                    {
                      "formatter": "DateTimeFormatter.ISO_LOCAL_TIME",
                      "pattern": "HH:mm:ss",
                      "example": "13:25:30"
                    },
                    {
                      "formatter": "DateTimeFormatter.ISO_TIME",
                      "pattern": "HH:mm:ssXXX",
                      "example": "13:25:30-04:00"
                    },
                    {
                      "formatter": "DateTimeFormatter.ISO_OFFSET_TIME",
                      "pattern": "HH:mm:ssXXX",
                      "example": "13:25:30-04:00"
                    },
                    {
                      "formatter": "DateTimeFormatter.ofPattern(\\"HH:mm:ss\\")",
                      "pattern": "HH:mm:ss",
                      "example": "13:25:30"
                    },
                    {
                      "formatter": "DateTimeFormatter.ofPattern(\\"HH:mm\\")",
                      "pattern": "HH:mm",
                      "example": "13:25"
                    },
                    {
                      "formatter": "DateTimeFormatter.ofPattern(\\"h:mm a\\")",
                      "pattern": "h:mm a",
                      "example": "1:25 PM"
                    },
                    {
                      "formatter": "DateTimeFormatter.ofPattern(\\"h:mm:ss a\\")",
                      "pattern": "h:mm:ss a",
                      "example": "1:25:30 PM"
                    },
                    {
                      "formatter": "DateTimeFormatter.ofPattern(\\"H:mm:ss\\")",
                      "pattern": "H:mm:ss",
                      "example": "13:25:30"
                    },
                    {
                      "formatter": "DateTimeFormatter.ofPattern(\\"H:mm\\")",
                      "pattern": "H:mm",
                      "example": "13:25"
                    }
                  ]
                }
                """;
        assertJson(testJson).expect(json -> {
            json.nodes("/time_formats").stream().forEach(node -> {
                node.node("/example").to.be.timeType();
                node.node("/example").to.be.time.sameOrAfter(LocalTime.of(13, 25));
                node.node("/example").to.be.time.before(LocalTime.of(13, 26));
            });
        });
    }
}
