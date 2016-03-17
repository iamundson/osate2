package org.osate.xtext.aadl2.instance.tests

import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith
import org.osate.aadl2.AadlPackage
import org.osate.xtext.aadl2.instance.InstanceUiInjectorProvider

@RunWith(XtextRunner)
@InjectWith(InstanceUiInjectorProvider)
class SerializerTest2 extends AbstractSerializerTest {
	override getProjectName() {
		"SerializerTest2"
	}
	
	@Test
	def void testEndToEndFlows() {
		val pkg1FileName = "pkg1.aadl"
		createFiles(pkg1FileName -> '''
			package pkg1
			public
				system s1
					features
						p1: in data port;
						p2: out data port;
					flows
						f1: flow path p1 -> p2;
				end s1;
				
				system s2
					features
						p3: in data port;
					flows
						f2: flow sink p3;
				end s2;
				
				system s3
					features
						p4: in data port;
						p5: out data port;
					flows
						f3: flow sink p4;
						f4: flow sink p4;
						f5: flow path p4 -> p5;
				end s3;
				
				system implementation s3.i
					subcomponents
						s1_sub: system s1;
						s2_sub: system s2;
					connections
						conn1: port p4 -> s1_sub.p1;
						conn2: port s1_sub.p2 -> s2_sub.p3;
					flows
						f3: flow sink p4 -> conn1 -> s1_sub.f1 -> conn2 -> s2_sub.f2;
						f4: flow sink p4 -> conn1 -> s1_sub -> conn2 -> s2_sub.f2;
						etef1: end to end flow s1_sub -> conn2 -> s2_sub.f2;
				end s3.i;
				
				system s4
					features
						p6: out data port;
						p7: in data port;
					flows
						f6: flow source p6;
						f7: flow sink p7;
				end s4;
				
				system s5
				end s5;
				
				system implementation s5.i
					subcomponents
						s4_sub: system s4;
						s3_sub: system s3.i;
					connections
						conn3: port s4_sub.p6 -> s3_sub.p4;
						conn4: port s3_sub.p5 -> s4_sub.p7;
					flows
						etef2: end to end flow s4_sub.f6 -> conn3 -> s3_sub.f3;
						etef3: end to end flow s4_sub.f6 -> conn3 -> s3_sub.f4;
						etef4: end to end flow s4_sub.f6 -> conn3 -> s3_sub.f5;
						etef5: end to end flow etef4 -> conn4 -> s4_sub.f7;
				end s5.i;
			end pkg1;
		''')
		suppressSerialization
		assertSerialize(testFile(pkg1FileName).resource.contents.head as AadlPackage, "s5.i", '''
			system s5_i_Instance : pkg1::s5.i {
				system s4_sub [ 0 ] : pkg1::s5.i::s4_sub {
					out dataPort p6 : pkg1::s4::p6 source of ( 1~0 ) destination of ( f6 )
					in dataPort p7 : pkg1::s4::p7 source of ( f7 ) destination of ( 1~1 )
					flow f6 ( -> p6 ) : pkg1::s4::f6
					flow f7 ( p7 -> ) : pkg1::s4::f7
				}
				system s3_sub [ 0 ] : pkg1::s5.i::s3_sub {
					in dataPort p4 : pkg1::s3::p4 source of ( f3 , f4 , f5 )
					out dataPort p5 : pkg1::s3::p5 source of ( 1~1 ) destination of ( f5 )
					system s1_sub [ 0 ] : pkg1::s3.i::s1_sub {
						in dataPort p1 : pkg1::s1::p1 source of ( f1 ) destination of ( 2~0 )
						out dataPort p2 : pkg1::s1::p2 source of ( 1~0 ) destination of ( f1 )
						flow f1 ( p1 -> p2 ) : pkg1::s1::f1
					}
					system s2_sub [ 0 ] : pkg1::s3.i::s2_sub {
						in dataPort p3 : pkg1::s2::p3 source of ( f2 ) destination of ( 1~0 )
						flow f2 ( p3 -> ) : pkg1::s2::f2
					}
					complete portConnection "s1_sub.p2 -> s2_sub.p3" : s1_sub[0].p2 ->
					s2_sub[0].p3 {
						s1_sub[0].p2 -> s2_sub[0].p3 : pkg1::s3.i::conn2 in parent
					}
					flow f3 ( p4 -> ) : pkg1::s3::f3
					flow f4 ( p4 -> ) : pkg1::s3::f4
					flow f5 ( p4 -> p5 ) : pkg1::s3::f5
					end to end flow etef1 s1_sub[0] -> 0 -> s2_sub[0].f2 : pkg1::s3.i::etef1
				}
				complete portConnection "s4_sub.p6 -> s3_sub.s1_sub.p1" : s4_sub[0].p6 ->
				s3_sub[0].s1_sub[0].p1 {
					s4_sub[0].p6 -> s3_sub[0].p4 : pkg1::s5.i::conn3 in parent
					s3_sub[0].p4 -> s3_sub[0].s1_sub[0].p1 : pkg1::s3.i::conn1 in s3_sub[0]
				}
				complete portConnection "s3_sub.p5 -> s4_sub.p7" : s3_sub[0].p5 ->
				s4_sub[0].p7 {
					s3_sub[0].p5 -> s4_sub[0].p7 : pkg1::s5.i::conn4 in parent
				}
				end to end flow etef2 s4_sub[0].f6 -> 0 -> s3_sub[0].s1_sub[0].f1 ->
				s3_sub[0].0 -> s3_sub[0].s2_sub[0].f2 : pkg1::s5.i::etef2
				end to end flow etef3 s4_sub[0].f6 -> 0 -> s3_sub[0].s1_sub[0] -> s3_sub[0].0
				-> s3_sub[0].s2_sub[0].f2 : pkg1::s5.i::etef3
				end to end flow etef4 s4_sub[0].f6 -> 0 -> s3_sub[0].f5 : pkg1::s5.i::etef4
				end to end flow etef5 etef4 -> 1 -> s4_sub[0].f7 : pkg1::s5.i::etef5
				som "No Modes"
			}''')
	}
	
	@Test
	def void testModes() {
		val pkg1FileName = "pkg1.aadl"
		createFiles(pkg1FileName -> '''
			package pkg1
			public
				system s1
					modes
						m1: initial mode;
						m2: mode;
				end s1;
				
				system s2
					requires modes
						m3: initial mode;
						m4: mode;
				end s2;
				
				system s3
					modes
						m5: initial mode;
						m6: mode;
				end s3;
				
				system implementation s3.i
					subcomponents
						s1_sub: system s1;
						s2_sub: system s2;
				end s3.i;
			end pkg1;
		''')
		suppressSerialization
		assertSerialize(testFile(pkg1FileName).resource.contents.head as AadlPackage, "s3.i", '''
			system s3_i_Instance : pkg1::s3.i {
				system s1_sub [ 0 ] : pkg1::s3.i::s1_sub {
					initial mode m1 : pkg1::s1::m1
					mode m2 : pkg1::s1::m2
				}
				system s2_sub [ 0 ] : pkg1::s3.i::s2_sub {
					initial derived mode m3 : pkg1::s2::m3
					derived mode m4 : pkg1::s2::m4
				}
				initial mode m5 : pkg1::s3::m5
				mode m6 : pkg1::s3::m6
				som "m5#s1_sub.m1" m5 , s1_sub[0].m1
				som "m5#s1_sub.m2" m5 , s1_sub[0].m2
				som "m6#s1_sub.m1" m6 , s1_sub[0].m1
				som "m6#s1_sub.m2" m6 , s1_sub[0].m2
			}''')
	}
	
	@Test
	def void testInModes() {
		val pkg1FileName = "pkg1.aadl"
		createFiles(pkg1FileName -> '''
			package pkg1
			public
				system s1
					features
						p1: in data port;
					flows
						f1: flow sink p1 in modes (m1, m2);
					modes
						m1: initial mode;
						m2: mode;
						m3: mode;
				end s1;
				
				system implementation s1.i
					subcomponents
						sub1: system s2;
						sub2: system in modes (m1);
						sub3: system in modes (m1, m2);
						sub4: system s3 in modes (m1, m2, m3);
					connections
						conn1: port sub1.p2 -> sub4.p3 in modes (m3);
					flows
						etef1: end to end flow sub1.f2 -> conn1 -> sub4.f3 in modes (m3);
				end s1.i;
				
				system s2
					features
						p2: out data port;
					flows
						f2: flow source p2;
				end s2;
				
				system s3
					features
						p3: in data port;
					flows
						f3: flow sink p3;
				end s3;
			end pkg1;
		''')
		suppressSerialization
		assertSerialize(testFile(pkg1FileName).resource.contents.head as AadlPackage, "s1.i", '''
			system s1_i_Instance : pkg1::s1.i {
				in dataPort p1 : pkg1::s1::p1 source of ( f1 )
				system sub1 [ 0 ] : pkg1::s1.i::sub1 {
					out dataPort p2 : pkg1::s2::p2 source of ( 1~0 ) destination of ( f2 )
					flow f2 ( -> p2 ) : pkg1::s2::f2
				}
				system sub2 [ 0 ] in modes ( m1 ) : pkg1::s1.i::sub2
				system sub3 [ 0 ] in modes ( m1 , m2 ) : pkg1::s1.i::sub3
				system sub4 [ 0 ] in modes ( m1 , m2 , m3 ) : pkg1::s1.i::sub4 {
					in dataPort p3 : pkg1::s3::p3 source of ( f3 ) destination of ( 1~0 )
					flow f3 ( p3 -> ) : pkg1::s3::f3
				}
				complete portConnection "sub1.p2 -> sub4.p3" : sub1[0].p2 -> sub4[0].p3 in
				modes ( 2 ) {
					sub1[0].p2 -> sub4[0].p3 : pkg1::s1.i::conn1 in parent
				}
				flow f1 ( p1 -> ) in modes ( m1 , m2 ) : pkg1::s1::f1
				end to end flow etef1 sub1[0].f2 -> 0 -> sub4[0].f3 in modes ( 2 ) :
				pkg1::s1.i::etef1
				initial mode m1 : pkg1::s1::m1
				mode m2 : pkg1::s1::m2
				mode m3 : pkg1::s1::m3
				som "m1" m1
				som "m2" m2
				som "m3" m3
			}''')
	}
	
	@Test
	def void testParentMode() {
		val pkg1FileName = "pkg1.aadl"
		createFiles(pkg1FileName -> '''
			package pkg1
			public
				system s1
					requires modes
						m1: initial mode;
						m2: mode;
				end s1;
				
				system s2
					requires modes
						m3: initial mode;
						m4: mode;
						m5: mode;
				end s2;
				
				system implementation s2.i
					subcomponents
						sub1: system s1 in modes (m3 => m1, m4 => m2);
				end s2.i;
				
				system s3
					modes
						m6: initial mode;
						m7: mode;
						m5: mode;
				end s3;
				
				system implementation s3.i
					subcomponents
						sub2: system s2.i in modes (m6 => m3, m7 => m3, m5);
				end s3.i;
			end pkg1;
		''')
		suppressSerialization
		assertSerialize(testFile(pkg1FileName).resource.contents.head as AadlPackage, "s3.i", '''
			system s3_i_Instance : pkg1::s3.i {
				system sub2 [ 0 ] in modes ( m6 , m7 , m5 ) : pkg1::s3.i::sub2 {
					system sub1 [ 0 ] in modes ( m3 , m4 ) : pkg1::s2.i::sub1 {
						initial derived mode m1 = m3 : pkg1::s1::m1
						derived mode m2 = m4 : pkg1::s1::m2
					}
					initial derived mode m3 = ( m6 , m7 ) : pkg1::s2::m3
					derived mode m4 : pkg1::s2::m4
					derived mode m5 = m5 : pkg1::s2::m5
				}
				initial mode m6 : pkg1::s3::m6
				mode m7 : pkg1::s3::m7
				mode m5 : pkg1::s3::m5
				som "m6#sub2.m3" m6 , sub2[0].m3
				som "m7#sub2.m3" m7 , sub2[0].m3
				som "m5#sub2.m5" m5 , sub2[0].m5
			}''')
	}
	
	@Test
	def void testModeTransitionInstances() {
		val pkg1FileName = "pkg1.aadl"
		createFiles(pkg1FileName -> '''
			package pkg1
			public
				system s
					features
						p1: in event port;
					modes
						m1: initial mode;
						m2: mode;
						mt1: m1 -[p1]-> m2;
				end s;
				
				system implementation s.i
					subcomponents
						sub1: system s;
				end s.i;
			end pkg1;
		''')
		suppressSerialization
		assertSerialize(testFile(pkg1FileName).resource.contents.head as AadlPackage, "s.i", '''
			system s_i_Instance : pkg1::s.i {
				in eventPort p1 : pkg1::s::p1
				system sub1 [ 0 ] : pkg1::s.i::sub1 {
					in eventPort p1 : pkg1::s::p1
					initial mode m1 source of ( 0 ) : pkg1::s::m1
					mode m2 destination of ( 0 ) : pkg1::s::m2
					mode transition m1.p1.m2 m1 -> m2 : pkg1::s::mt1
				}
				initial mode m1 source of ( 0 ) : pkg1::s::m1
				mode m2 destination of ( 0 ) : pkg1::s::m2
				mode transition m1.p1.m2 m1 -> m2 : pkg1::s::mt1
				som "m1#sub1.m1" m1 , sub1[0].m1
				som "m1#sub1.m2" m1 , sub1[0].m2
				som "m2#sub1.m1" m2 , sub1[0].m1
				som "m2#sub1.m2" m2 , sub1[0].m2
			}''')
	}
	
	@Test
	def void testInModeTransitions() {
		val pkg1FileName = "pkg1.aadl"
		createFiles(pkg1FileName -> '''
			package pkg1
			public
				system s1
					features
						p1: out data port;
				end s1;
				
				system s2
					features
						p2: in data port;
				end s2;
				
				system s3
					features
						p3: in event port;
					flows
						f1: flow sink p3 in modes (mt1);
					modes
						m1: initial mode;
						m2: mode;
						mt1: m1 -[p3]-> m2;
				end s3;
				
				system implementation s3.i
					subcomponents
						sub1: system s1;
						sub2: system s2;
					connections
						conn1: port sub1.p1 -> sub2.p2 in modes (mt1);
				end s3.i;
			end pkg1;
		''')
		suppressSerialization
		assertSerialize(testFile(pkg1FileName).resource.contents.head as AadlPackage, "s3.i", '''
			system s3_i_Instance : pkg1::s3.i {
				in eventPort p3 : pkg1::s3::p3 source of ( f1 )
				system sub1 [ 0 ] : pkg1::s3.i::sub1 {
					out dataPort p1 : pkg1::s1::p1 source of ( 1~0 )
				}
				system sub2 [ 0 ] : pkg1::s3.i::sub2 {
					in dataPort p2 : pkg1::s2::p2 destination of ( 1~0 )
				}
				complete portConnection "sub1.p1 -> sub2.p2" : sub1[0].p1 -> sub2[0].p2 in
				transitions ( 0 ) {
					sub1[0].p1 -> sub2[0].p2 : pkg1::s3.i::conn1 in parent
				}
				flow f1 ( p3 -> ) in transitions ( 0 ) : pkg1::s3::f1
				initial mode m1 source of ( 0 ) : pkg1::s3::m1
				mode m2 destination of ( 0 ) : pkg1::s3::m2
				mode transition m1.p3.m2 m1 -> m2 : pkg1::s3::mt1
				som "m1" m1
				som "m2" m2
			}''')
	}
	
	@Test
	def void testPropertyAssociations() {
		val ps1FileName = "ps1.aadl"
		val pkg1FileName = "pkg1.aadl"
		createFiles(ps1FileName -> '''
			property set ps1 is
				bool1: aadlboolean applies to (all);
			end ps1;
		''', pkg1FileName -> '''
			package pkg1
			public
				with ps1;
				
				system s1
					features
						p1: out data port;
						p2: in event port;
						fg1: feature group fgt1;
					flows
						fs1: flow source p1 {ps1::bool1 => true;};
					modes
						m1: initial mode {ps1::bool1 => true;};
						m2: mode;
						mt1: m1 -[p2]-> m2 {ps1::bool1 => true;};
						m2 -[p2]-> m1 {ps1::bool1 => true;};
				end s1;
				
				system s2
					features
						p3: in data port;
					flows
						fs2: flow sink p3;
				end s2;
				
				system s3
					properties
						ps1::bool1 => true;
				end s3;
				
				system implementation s3.i
					subcomponents
						sub1: system s1 {ps1::bool1 => true;};
						sub2: system s2;
					connections
						conn1: port sub1.p1 -> sub2.p3 {ps1::bool1 => true;};
					flows
						etef1: end to end flow sub1.fs1 -> conn1 -> sub2.fs2 {ps1::bool1 => true;};
				end s3.i;
				
				feature group fgt1
					features
						p4: in data port {ps1::bool1 => true;};
				end fgt1;
			end pkg1;
		''')
		suppressSerialization
		assertSerialize(testFile(pkg1FileName).resource.contents.head as AadlPackage, "s3.i", '''
			system s3_i_Instance : pkg1::s3.i {
				system sub1 [ 0 ] : pkg1::s3.i::sub1 {
					in out featureGroup fg1 : pkg1::s1::fg1 {
						in dataPort p4 : pkg1::fgt1::p4 {
							ps1::bool1 => true : pkg1::fgt1::p4::0
						}
					}
					out dataPort p1 : pkg1::s1::p1 source of ( 1~0 ) destination of ( fs1 )
					in eventPort p2 : pkg1::s1::p2
					flow fs1 ( -> p1 ) : pkg1::s1::fs1 {
						ps1::bool1 => true : pkg1::s1::fs1::0
					}
					initial mode m1 source of ( 0 ) destination of ( 1 ) : pkg1::s1::m1 {
						ps1::bool1 => true : pkg1::s1::m1::0
					}
					mode m2 source of ( 1 ) destination of ( 0 ) : pkg1::s1::m2
					mode transition m1.p2.m2 m1 -> m2 : pkg1::s1::mt1 {
						ps1::bool1 => true : pkg1::s1::mt1::0
					}
					mode transition m2.p2.m1 m2 -> m1 : pkg1::s1::transition#1 {
						ps1::bool1 => true : pkg1::s1::transition#1::0
					}
					ps1::bool1 => true : pkg1::s3.i::sub1::0
				}
				system sub2 [ 0 ] : pkg1::s3.i::sub2 {
					in dataPort p3 : pkg1::s2::p3 source of ( fs2 ) destination of ( 1~0 )
					flow fs2 ( p3 -> ) : pkg1::s2::fs2
				}
				complete portConnection "sub1.p1 -> sub2.p3" : sub1[0].p1 -> sub2[0].p3 {
					sub1[0].p1 -> sub2[0].p3 : pkg1::s3.i::conn1 in parent
					ps1::bool1 => true : pkg1::s3.i::conn1::0
				}
				end to end flow etef1 sub1[0].fs1 -> 0 -> sub2[0].fs2 in modes ( 0 , 1 ) :
				pkg1::s3.i::etef1 {
					ps1::bool1 => true : pkg1::s3.i::etef1::0
				}
				som "sub1.m1" sub1[0].m1
				som "sub1.m2" sub1[0].m2
				ps1::bool1 => true : pkg1::s3::0
			}''')
	}
	
	@Test
	def void testModalProperties() {
		val ps1FileName = "ps1.aadl"
		val pkg1FileName = "pkg1.aadl"
		createFiles(ps1FileName -> '''
			property set ps1 is
				bool1: aadlboolean applies to (all);
			end ps1;
		''', pkg1FileName -> '''
			package pkg1
			public
				with ps1;
				
				system s
					modes
						m1: initial mode;
						m2: mode;
					properties
						ps1::bool1 => true in modes (m1), false in modes(m2);
				end s;
				
				system implementation s.i
					subcomponents
						sub1: system s;
				end s.i;
			end pkg1;
		''')
		suppressSerialization
		assertSerialize(testFile(pkg1FileName).resource.contents.head as AadlPackage, "s.i", '''
			system s_i_Instance : pkg1::s.i {
				system sub1 [ 0 ] : pkg1::s.i::sub1 {
					initial mode m1 : pkg1::s::m1
					mode m2 : pkg1::s::m2
					ps1::bool1 => true in modes ( 0 , 2 ) , false in modes ( 1 , 3 ) : pkg1::s::0
				}
				initial mode m1 : pkg1::s::m1
				mode m2 : pkg1::s::m2
				som "m1#sub1.m1" m1 , sub1[0].m1
				som "m1#sub1.m2" m1 , sub1[0].m2
				som "m2#sub1.m1" m2 , sub1[0].m1
				som "m2#sub1.m2" m2 , sub1[0].m2
				ps1::bool1 => true in modes ( 0 , 1 ) , false in modes ( 2 , 3 ) : pkg1::s::0
			}''')
	}
	
	@Test
	def void testPropertyTypes() {
		val ps1FileName = "ps1.aadl"
		val pkg1FileName = "pkg1.aadl"
		createFiles(ps1FileName -> '''
			property set ps1 is
				enumType1: type enumeration (one, two, three);
				units1: type units (mm, cm => mm * 10, m => cm * 100);
				
				const1: constant aadlinteger => 42;
				
				bool1: aadlboolean applies to (all);
				string1: aadlstring applies to (all);
				int1: aadlinteger applies to (all);
				int2: aadlinteger applies to (all);
				int3: aadlinteger units ps1::units1 applies to (all);
				real1: aadlreal applies to (all);
				real2: aadlreal applies to (all);
				real3: aadlreal units ps1::units1 applies to (all);
				range1: range of aadlinteger applies to (all);
				range2: range of aadlinteger applies to (all);
				range3: range of aadlinteger units ps1::units1 applies to (all);
				int4: aadlinteger applies to (all);
				int5: aadlinteger applies to (all);
				enum1: ps1::enumType1 applies to (all);
				classifier1: classifier (system) applies to (all);
				int6: aadlinteger applies to (all);
				record1: record (
					field1: aadlinteger;
					field2: record (field3: aadlinteger;);
					field4: ps1::enumType1;
					field5: aadlinteger units ps1::units1;
					field6: range of aadlinteger units ps1::units1;
				) applies to (all);
				list1: list of aadlinteger applies to (all);
			end ps1;
		''', pkg1FileName -> '''
			package pkg1
			public
				with ps1;
				
				system s
					properties
						ps1::bool1 => true;
						ps1::string1 => "value";
						ps1::int1 => 42;
						ps1::int2 => -42;
						ps1::int3 => 42cm;
						ps1::real1 => 3.14;
						ps1::real2 => -3.14;
						ps1::real3 => 3.14cm;
						ps1::range1 => 0 .. 10;
						ps1::range2 => 0 .. 10 delta 2;
						ps1::range3 => 0cm .. 10cm;
						ps1::int4 => ps1::const1;
						ps1::int5 => ps1::int1;
						ps1::enum1 => two;
						ps1::classifier1 => classifier (s);
						ps1::int6 => compute (function1);
						ps1::record1 => [
							field1 => 42;
							field2 => [field3 => 42;];
							field4 => two;
							field5 => 42cm;
							field6 => 0cm .. 10cm;
						];
						ps1::list1 => (1, 2, 4, 8);
				end s;
				
				system implementation s.i
				end s.i;
			end pkg1;
		''')
		suppressSerialization
		assertSerialize(testFile(pkg1FileName).resource.contents.head as AadlPackage, "s.i", '''
			system s_i_Instance : pkg1::s.i {
				som "No Modes"
				ps1::bool1 => true : pkg1::s::0
				ps1::string1 => "value" : pkg1::s::1
				ps1::int1 => 42 : pkg1::s::2
				ps1::int2 => -42 : pkg1::s::3
				ps1::int3 => 42 cm : pkg1::s::4
				ps1::real1 => 3.14 : pkg1::s::5
				ps1::real2 => -3.14 : pkg1::s::6
				ps1::real3 => 3.14 cm : pkg1::s::7
				ps1::range1 => 0 .. 10 : pkg1::s::8
				ps1::range2 => 0 .. 10 delta 2 : pkg1::s::9
				ps1::range3 => 0 cm .. 10 cm : pkg1::s::10
				ps1::int4 => ps1::const1 : pkg1::s::11
				ps1::int5 => ps1::int1 : pkg1::s::12
				ps1::enum1 => two : pkg1::s::13
				ps1::classifier1 => classifier ( pkg1::s ) : pkg1::s::14
				ps1::int6 => compute ( function1 ) : pkg1::s::15
				ps1::record1 => [ field1 => 42 ; field2 => [ field3 => 42 ; ] ; field4 => two
				; field5 => 42 cm ; field6 => 0 cm .. 10 cm ; ] : pkg1::s::16
				ps1::list1 => ( 1 , 2 , 4 , 8 ) : pkg1::s::17
			}''')
	}
	
	@Test
	def void testDeclarativeReferenceValue() {
		val ps1FileName = "ps1.aadl"
		val pkg1FileName = "pkg1.aadl"
		createFiles(ps1FileName -> '''
			property set ps1 is
				reference1: reference (named element) applies to (all);
				reference2: reference (named element) applies to (all);
				reference3: reference (named element) applies to (all);
				reference4: reference (named element) applies to (all);
				reference5: reference (named element) applies to (all);
				reference6: reference (named element) applies to (all);
				reference7: reference (named element) applies to (all);
				reference8: reference (named element) applies to (all);
				reference9: reference (named element) applies to (all);
				reference10: reference (named element) applies to (all);
			end ps1;
		''', pkg1FileName -> '''
			package pkg1
			public
				with ps1;
				
				system s1
					prototypes
						proto1: system;
					features
						fg1: feature group fgt1;
						p1: in event port;
					modes
						m1: initial mode;
						m2: mode;
						mt1: m1-[p1]->m2;
				end s1;
				
				system implementation s1.i
					subcomponents
						sub1: system s2;
						sub2: abstract a1.i;
					internal features
						es1: event;
					processor features
						pp1: port;
					properties
						ps1::reference1 => reference (proto1);
						ps1::reference2 => reference (fg1.proto2);
						ps1::reference3 => reference (fg1.fg2.proto3);
						ps1::reference4 => reference (fg1.fg3[1].proto3);
						ps1::reference5 => reference (sub1.proto4);
						ps1::reference6 => reference (es1);
						ps1::reference7 => reference (pp1);
						ps1::reference8 => reference (sub2.sequence1);
						ps1::reference9 => reference (sub2.call1);
						ps1::reference10 => reference (mt1);
				end s1.i;
				
				feature group fgt1
					prototypes
						proto2: system;
					features
						fg2: feature group fgt2;
						fg3: feature group fgt2[2];
				end fgt1;
				
				feature group fgt2
					prototypes
						proto3: system;
				end fgt2;
				
				system s2
					prototypes
						proto4: system;
				end s2;
				
				abstract a1
					features
						sa1: requires subprogram access;
				end a1;
				
				abstract implementation a1.i
					calls
						sequence1: {
							call1: subprogram sa1;
						};
				end a1.i;
			end pkg1;
		''')
		suppressSerialization
		assertSerialize(testFile(pkg1FileName).resource.contents.head as AadlPackage, "s1.i", '''
			system s1_i_Instance : pkg1::s1.i {
				in out featureGroup fg1 : pkg1::s1::fg1 {
					in out featureGroup fg2 : pkg1::fgt1::fg2
					in out featureGroup fg3 [ 1 ] : pkg1::fgt1::fg3
					in out featureGroup fg3 [ 2 ] : pkg1::fgt1::fg3
				}
				in eventPort p1 : pkg1::s1::p1
				abstract sub2 [ 0 ] : pkg1::s1.i::sub2 {
					in out subprogramAccess sa1 : pkg1::a1::sa1
				}
				system sub1 [ 0 ] : pkg1::s1.i::sub1
				initial mode m1 source of ( 0 ) : pkg1::s1::m1
				mode m2 destination of ( 0 ) : pkg1::s1::m2
				mode transition m1.p1.m2 m1 -> m2 : pkg1::s1::mt1
				som "m1" m1
				som "m2" m2
				ps1::reference1 => reference ( pkg1::s1::proto1 ) : pkg1::s1.i::0
				ps1::reference2 => reference ( pkg1::s1::fg1 / pkg1::fgt1::proto2 ) :
				pkg1::s1.i::1
				ps1::reference3 => reference ( pkg1::s1::fg1 / pkg1::fgt1::fg2 /
				pkg1::fgt2::proto3 ) : pkg1::s1.i::2
				ps1::reference4 => reference ( pkg1::s1::fg1 / pkg1::fgt1::fg3 [ 1 ] /
				pkg1::fgt2::proto3 ) : pkg1::s1.i::3
				ps1::reference5 => reference ( pkg1::s1.i::sub1 / pkg1::s2::proto4 ) :
				pkg1::s1.i::4
				ps1::reference6 => reference ( pkg1::s1.i::es1 ) : pkg1::s1.i::5
				ps1::reference7 => reference ( pkg1::s1.i::pp1 ) : pkg1::s1.i::6
				ps1::reference8 => reference ( pkg1::s1.i::sub2 / pkg1::a1.i::sequence1 ) :
				pkg1::s1.i::7
				ps1::reference9 => reference ( pkg1::s1.i::sub2 / pkg1::a1.i::call1 ) :
				pkg1::s1.i::8
				ps1::reference10 => reference ( pkg1::s1::mt1 ) : pkg1::s1.i::9
			}''')
	}
	
	@Test
	def void testInstanceReferenceValue() {
		val ps1FileName = "ps1.aadl"
		val pkg1FileName = "pkg1.aadl"
		createFiles(ps1FileName -> '''
			property set ps1 is
				reference1: reference (named element) applies to (all);
				reference2: reference (named element) applies to (all);
				reference3: reference (named element) applies to (all);
				reference4: reference (named element) applies to (all);
				reference5: reference (named element) applies to (all);
				reference6: reference (named element) applies to (all);
			end ps1;
		''', pkg1FileName -> '''
			package pkg1
			public
				with ps1;
				
				system s1
					features
						p1: out data port;
				end s1;
				
				system s2
					features
						p2: in data port;
					flows
						f1: flow sink p2;
				end s2;
				
				system s3
					modes
						m1: initial mode;
				end s3;
				
				system implementation s3.i
					subcomponents
						sub1: system s1;
						sub2: system s2;
					connections
						conn1: port sub1.p1 -> sub2.p2;
					flows
						etef1: end to end flow sub1 -> conn1 -> sub2.f1;
					properties
						ps1::reference1 => reference (sub1.p1);
						ps1::reference2 => reference (sub2.f1);
						ps1::reference3 => reference (m1);
						ps1::reference4 => reference (sub1);
						ps1::reference5 => reference (conn1);
						ps1::reference6 => reference (etef1);
				end s3.i;
			end pkg1;
		''')
		suppressSerialization
		assertSerialize(testFile(pkg1FileName).resource.contents.head as AadlPackage, "s3.i", '''
			system s3_i_Instance : pkg1::s3.i {
				system sub1 [ 0 ] : pkg1::s3.i::sub1 {
					out dataPort p1 : pkg1::s1::p1 source of ( 1~0 )
				}
				system sub2 [ 0 ] : pkg1::s3.i::sub2 {
					in dataPort p2 : pkg1::s2::p2 source of ( f1 ) destination of ( 1~0 )
					flow f1 ( p2 -> ) : pkg1::s2::f1
				}
				complete portConnection "sub1.p1 -> sub2.p2" : sub1[0].p1 -> sub2[0].p2 {
					sub1[0].p1 -> sub2[0].p2 : pkg1::s3.i::conn1 in parent
				}
				end to end flow etef1 sub1[0] -> 0 -> sub2[0].f1 : pkg1::s3.i::etef1
				initial mode m1 : pkg1::s3::m1
				som "m1" m1
				ps1::reference1 => reference ( sub1[0].p1 ) : pkg1::s3.i::0
				ps1::reference2 => reference ( sub2[0].f1 ) : pkg1::s3.i::1
				ps1::reference3 => reference ( m1 ) : pkg1::s3.i::2
				ps1::reference4 => reference ( sub1[0] ) : pkg1::s3.i::3
				ps1::reference5 => reference ( 0 ) : pkg1::s3.i::4
				ps1::reference6 => reference ( etef1 ) : pkg1::s3.i::5
			}''')
	}
}