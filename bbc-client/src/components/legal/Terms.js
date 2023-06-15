import { PageHeader } from '../../app/layout/PageHeader';

const Terms = () => {
  return (
    <div className="app container-fluid" style={{ margin: 0, padding: 0 }}>
      <PageHeader page={'Terms of Service'} icon={'fa-solid fa-lock'} />
      <div className="app content container-fluid terms-main">
        <div
          id="terms-content"
          className="app container"
          style={{ maxWidth: '1200px' }}
        >
          <div className="overview">
            <h2>Universal Terms Of Service</h2>
            <h5>Updated: June 13, 2022</h5>
            <p>
              PLEASE READ THIS TERMS OF SERVICE AGREEMENT CAREFULLY. IT CONTAINS
              IMPORTANT INFORMATION REGARDING YOUR LEGAL RIGHTS AND REMEDIES
            </p>
          </div>
          <ol id="terms-list">
            <li>
              <h4>Overview</h4>
              <p>
                This Universal Terms of Service Agreement (this
                &quot;Agreement&quot;) is entered into by and between MD Web
                Technologies, jointly with its proprietor Martin Dwyer, and you.
                It is made effective as of the date of your use of this website
                at www.mdbytes.com (&quot;Site&quot;) or the date of electronic
                acceptance. This Agreement sets forth the general terms and
                conditions of your use of the Site and the products and services
              </p>
              <p>
                The terms “We”, “Us” or “Our” shall refer to MD Web Technologies
                jointly with proprietor Martin Dwyer. The terms “You”, “Your”,
                “User” or “Customer” shall refer to any individual or entity who
                uses this Site or has accepted the terms of this Agreement
                electronically.
              </p>
              <p>
                The term &quot;Services&quot; shall refer to the Websites, Web
                Applications, Software Development and Domain Services listed in
                our{' '}
                <a href="https://mdbytes.com/services/catalog">
                  Service Catalog
                </a>{' '}
                on this site.
              </p>
              <p>
                Your continued use of this Site is manifested consent to the
                terms of this Agreement.
              </p>
            </li>
            <li>
              <h4>Consent to Electronic Communications</h4>
              <p>
                By using this Site and/or Services offered herein, you consent
                to receiving certain electronic communications from us. You
                agree that any notices, agreements, disclosures, or other
                communications that we send to you electronically will satisfy
                any legal communication requirements, including that those
                communications be in writing.
              </p>
            </li>
            <li>
              <h4>Privacy Policy</h4>
              <p>
                This Agreement includes a <a href="/privacy">Privacy Policy</a>{' '}
                found on this site <a href="/privacy">here</a>, which
                constitutes agreements between You and Us related to Your
                privacy while using this Site or Services offered herein.
              </p>
            </li>

            <li>
              <h4>Modification of Agreement</h4>
              <p>
                We may, in our sole discretion, change or modify this Agreement,
                and any policies or agreements which are incorporated herein, at
                any time. Such changes or modifications shall be effective
                immediately upon posting to this Site. Your use of this Site or
                the Services after such changes or modifications have been made
                shall constitute your acceptance of this Agreement as last
                revised. If you do not agree to be bound by this Agreement as
                last revised, do not use (or continue to use) this Site or the
                Services.
              </p>
              <p>
                We may occasionally notify You of changes or modifications to
                this Agreement by email. It is therefore very important that you
                keep your account (“Account”) information current by sending Us
                updated information through Our{' '}
                <a href="/contact">Contact Page</a>.
              </p>
              <p>
                We assume no liability or responsibility for Your failure to
                receive an email notification if such failure results from an
                inaccurate email address in Your Account.
              </p>
              <p>
                We reserve the right to modify, change, or discontinue any
                aspect of this Site or the Services offered herein, including
                without limitation prices and fees for Services, at any time.
              </p>
            </li>
            <li>
              <h4>Eligibility</h4>

              <p>
                By using this Site or the Services offered herein, You represent
                and warrant that you are;
              </p>
              <ol className="eligibility-list">
                <li>at least eighteen (18) years of age,</li>
                <li>
                  recognized as being able to form legally binding contracts
                  under applicable law, and{' '}
                </li>
                <li>
                  not a person barred from purchasing or receiving the Services
                  found under the laws of the United States or applicable
                  jurisdiction.
                </li>
              </ol>

              <p>
                If you are entering into this Agreement on behalf of a corporate
                entity, You represent and warrant that You have the legal
                authority to bind such corporate entity to the terms and
                conditions contained in this Agreement, in which case the terms
                &quot;You&quot;, &quot;Your&quot;, &quot;User&quot; or
                &quot;Customer&quot; shall refer to such corporate entity.
              </p>
              <p>
                If, after your electronic acceptance of this Agreement, We find
                that You do not have the legal authority to bind such corporate
                entity, You will be personally responsible for the obligations
                under this Agreement.
              </p>
              <p>
                We shall not be liable for any loss or damage resulting from Our
                reliance on any instruction, notice, document or communication
                reasonably believed by Us to be genuine and originating from an
                authorized representative of Your corporate entity.
              </p>
              <p>
                If there is reasonable doubt about the authenticity of any such
                instruction, notice, document or communication, We reserve the
                right (but undertakes no duty) to require additional
                authentication from you.
              </p>
            </li>
            <li>
              <h4>Development Services</h4>
              <p>
                Website, Web Application, and Software Development services, as
                offered in Our{' '}
                <a href="https://mdbytes.com/services/catalog">
                  Service Catalog
                </a>
                , are referred to collectively as &quot;Development
                Services.&quot;
              </p>
              <p>
                If you are interested in obtaining Development Services, please
                contact Us by Our <a href="/contact">Contact Page</a>. We will
                clarify the services requested and, once clarified, You will
                receive an electronic invoice for the services. Once the initial
                payment is received, you will be contacted for an initial
                development interview.
              </p>
              <p>
                By continuing to use this Site, You agree to the following
                covenants with respect to Development Services:
              </p>
              <ol className="dev-services-list">
                <li className="dev-services-list-item">
                  <h5>Additional Agreements</h5>
                  <p>
                    Any additional agreements, which may include additions or
                    deletions from requirements or changes in pricing as
                    specified in the{' '}
                    <a href="https://mdbytes.com/services/catalog">
                      Service Catalog
                    </a>
                    , must be in writing. Should they exist, such agreements
                    cannot replace any of Your obligations as stated in this
                    Universal Terms of Service Agreement.
                  </p>
                </li>
                <li className="dev-services-list-item">
                  <h5>Five Phases</h5>
                  <p>
                    Every Development Service will have five sequential phases,
                    initiation, build, delivery, revision, and deployment as
                    follows;
                  </p>
                  <ol className="phase-list">
                    <li>
                      Initiation begins with Your initial purchase of a
                      Development Service, subsequent to which you will schedule
                      and participate in a development interview, in which;
                      <ol className="dev-interview">
                        <li>
                          Our representative will gather and clarify information
                          needed from You to complete the Development Service;
                        </li>
                        <li>
                          You will provide content deemed necessary for the
                          production of the Development Service;
                        </li>

                        <li>
                          Initiation phase concludes with the approval of Your
                          content provided.
                        </li>
                      </ol>
                    </li>
                    <li>
                      Build phase begins upon approval of Your provided content.
                      During the build phase, We may have need to raise a
                      question or clarify issues. Questions or requests for
                      clarification will be sent by email. During build phase,
                      the development service will be hosted on Our development
                      server. Build phase concludes when the development service
                      has been built and initial tests have been concluded that
                      the service meets written functional requirements.
                    </li>
                    <li>
                      Delivery of the finished Development Service will begin
                      the date the Development Service is made available for
                      Your review on our development server. During this initial
                      delivery period, You will be given 10 days to review the
                      build of Your development service. Unless agreed upon in
                      writing, the delivery phase will end 10 days after initial
                      delivery notification.
                    </li>
                    <li>
                      Revision phase begins immediately after the delivery
                      phase. During revision, We will make revisions based on
                      Your review comments collected from the delivery phase.
                      These revisions will focus on;
                      <ol className="revision-stuff">
                        <li>
                          Ensuring the project meets all stated functional
                          requirements; and
                        </li>
                        <li>
                          Changes in typography, color-schemes, or
                          user-interface components based on the first review.
                        </li>
                      </ol>
                      Except as it relates to limitations stated below, the
                      revision phase ends once all necessary revisions have been
                      addressed.
                    </li>
                    <li>
                      Deployment phase begins when the revision phase has been
                      completed. During this phase, We will;
                      <ol className="deployment-list">
                        <li>
                          Deploy the Development Service to one agreed upon
                          hosting location, referred to below simply as host
                          server.
                        </li>
                        <li>
                          Configure the host server for the development service.
                        </li>
                        <li>Submit the built code to the host server.</li>
                        <li>Review and test deployed Service.</li>
                      </ol>
                      <p>
                        While we provide a list of recommended hosting
                        providers, You are solely responsible for securing
                        hosting services. We do not warrant hosting services you
                        receive from third-party hosting providers. As detailed
                        later in this Agreement, you indemnify Us from any
                        liability that may arise from Your chosen third-party
                        hosting provider.
                      </p>
                      <p>
                        The development service terminates with deployment.
                        Unless agreed upon elsewhere in writing, Development
                        Services do not include ongoing monitoring or service of
                        built projects on Your host server.
                      </p>
                    </li>
                  </ol>

                  <p></p>
                </li>
                <li className="dev-services-list-item">
                  <h5>Submission of Content</h5>
                  <p>
                    You are responsible for submitting all copy, images, and
                    other content for your Development Service. All provided
                    content should be copies and not the originals. You agree to
                    respond to any request for content, feedback or approval
                    within ten (10) business days. All content must be submitted
                    electronically and We will not return any materials you
                    provide. Arrangements may be made for the delivery of files
                    that are too large to be transmitted electronically, but
                    additional fees may apply and the quoted completion date may
                    be extended.
                  </p>
                  <p>
                    We may in our sole discretion, require you to re-submit
                    images if we determine that the images are not of high
                    enough quality.
                  </p>
                  <p>
                    If you do not submit the content within ten (10) business
                    days, we will attempt to confirm your content submission is
                    complete, but we reserve the right to progress the site to
                    build stage
                  </p>
                  <p>
                    It is your responsibility to maintain independent back-up
                    copies of any materials you submit. We expressly disclaim
                    any liability or responsibility for any loss, damage or
                    destruction of any content or materials you submit.
                  </p>
                </li>
                <li className="dev-services-list-item">
                  <h5>Limitation of Services: Revisions</h5>
                  <p>
                    The following services are not included in any of the
                    Development Services, but may be agreed upon separately in
                    writing:
                  </p>
                  <ol className="revision-list">
                    <li>
                      Revisions in the text, images, files and other data
                      provided by You when the build phase initiated;
                    </li>
                    <li>
                      Photo manipulation services, such as cutting the image out
                      from the background, adding shadows, cleaning up the image
                      from dust and scratches, making images a uniform size, and
                      adjusting levels/brightness to match.
                    </li>
                    <li>
                      Revisions which go beyond the functional requirements of
                      each Development Service offering. Adding a video, for
                      example, is not included in any of the Website offerings
                      in Our{' '}
                      <a href="https://mdbytes.com/service/catalog">
                        Service Catalog
                      </a>
                      .
                    </li>
                  </ol>
                  <p>
                    For completion of revisions beyond the scope of this
                    Agreement or the services as they are offered, additional
                    fees apply at our standard hourly rate of $75 per hour with
                    a 1 hour minimum.
                  </p>
                </li>

                <li className="dev-services-list-item">
                  <h5>Intellectual Property Rights</h5>
                  <p>
                    It is solely Your responsibility to ensure that any and all
                    User Content provided to Us to perform the Services on Your
                    behalf does not infringe or violate the intellectual
                    property rights (including, but not limited to, trademarks,
                    trade names, copyrights, patents, domain registration
                    rights, trade secrets) or any other right of any third party
                    (including, but not limited to, rights of privacy and
                    contractual rights), and/or to ensure that you have acquired
                    any authorization(s) necessary to use intellectual property
                    (including, but not limited to, copyrights and trademarks)
                    or other proprietary information of third parties therein
                    included in the User Content.
                  </p>
                  <p>
                    We shall have no liability and You agree to defend and
                    indemnify Us against any actual or alleged claim that any
                    User Content provided by you infringes or violates any
                    rights of third parties, including, without limitation,
                    rights of publicity, rights of privacy, patents, copyrights,
                    trademarks, trade secrets, and/or licenses.
                  </p>
                  <p>
                    If, in Our sole discretion, You have provided User Content
                    that infringes or violates any rights of third parties, We
                    have the right to refuse publication of the content until
                    proper evidence of your rights to use the materials is
                    provided.
                  </p>
                </li>
              </ol>
            </li>
            <li>
              <h4>Domain Services</h4>
              <p>
                Domain Procurement, DNS Configuration, and Email Configuration
                services offered at this Site in Our{' '}
                <a href="https://mdbytes.com/services/catalog">
                  Service Catalog
                </a>{' '}
                are referred to collectively as &quot;Domain Services.&quot;
              </p>
              <p>
                If you are interested in obtaining Domain Services, please
                contact us by our <a href="/contact">Contact Page</a>. We will
                clarify the services requested and, once clarified, you will
                receive an electronic invoice for the services. Once payment is
                received, We will contact You to schedule Services.
              </p>
              <p>
                Our agreement related to Domain Services includes, but is not
                limited to, the following:
              </p>
              <ol className="domain-services-list">
                <li className="domain-services-list-item">
                  As part of Domain Procurement services we provide consultation
                  related to best practices for domain name selection. You are
                  solely responsible for the ultimate choice of Your domain
                  name.
                </li>
                <li className="domain-services-list-item">
                  We do not purchase a domain as part of Domain Procurement.
                  Instead We provide You guidance in establishing an account
                  with a Domain Name Registrar. You will be solely responsible
                  for paying the Domain Name Registrar annual fees as required
                  to secure and retain your domain.
                </li>
                <li className="domain-services-list-item">
                  You are solely responsible for selection of a Domain Name
                  Registrar, establishing Your account with that registrar, and
                  understanding their terms of service and any limitations
                  related to their warranties or liabilities.
                </li>
                <li className="domain-services-list-item">
                  We make no warranty for services You obtain from a third-party
                  Domain Name Registrar.
                </li>
                <li className="domain-services-list-item">
                  You agree to indemnify Us from any liability that may arise as
                  a result of Your doing business with a third-party Domain Name
                  Registrar.
                </li>
              </ol>
            </li>
            <li>
              <h4>Restrictions for All Services</h4>
              <p>
                You acknowledge and agree that you will not use any Services in
                a manner, as determined by us in our sole and absolute
                discretion:
              </p>
              <ol className="restrictions-list">
                <li className="restrictions-list-item">
                  To display or advertise pornographic, X-rated, sexually
                  explicit, or otherwise tasteless materials, descriptions,
                  images, products or services (including, but not limited to
                  escort or prostitution services);
                </li>
                <li className="restrictions-list-item">
                  To sell or promote stolen or illegal items and/or to
                  facilitate or instruct others to engage in illegal activities;
                </li>
                <li className="restrictions-list-item">
                  To sell or promote products that infringe on third party
                  rights (e.g. copyright or trademarks) or counterfeit goods as
                  they mimic the brand features of the product in an attempt to
                  pass themselves off as a genuine product of the brand owner;
                </li>
                <li className="restrictions-list-item">
                  To sell or promote controlled substances and illegal drugs
                  (including prescription drugs), items used to manufacture
                  controlled substances and illegal drugs, and drug
                  paraphernalia;
                </li>
                <li className="restrictions-list-item">
                  To sell or promote products that have been recalled by the
                  Consumer Product Safety Commission.
                </li>
                <li className="restrictions-list-item">
                  To sell or promote content related to or products derived from
                  threatened or extinct species.
                </li>
                <li className="restrictions-list-item">
                  To promote offline or online gambling or the instruction of
                  gambling;
                </li>
                <li className="restrictions-list-item">
                  To promote content that may be deemed as capitalizing on or
                  lacking reasonable sensitivity towards a natural disaster,
                  conflict, death, or other tragic event.
                </li>
                <li className="restrictions-list-item">
                  To promote content that incites or endorses hatred against
                  others. Content that inappropriately discriminates against a
                  person or group or that seeks to intimidate, exploit, or
                  humiliate others.
                </li>
                <li className="restrictions-list-item">
                  To promote content containing violent language, gruesome or
                  disgusting imagery, or accounts of physical trauma. Content
                  containing gratuitous portrayals of bodily fluids or waste.
                </li>
                <li className="restrictions-list-item">
                  To promote content containing obscene or profane language or
                  content that are likely to shock or scare.
                </li>
              </ol>
              <p>
                You are responsible for ensuring that any content on your
                Development Service is in compliance with all applicable laws
                and regulations where your Development Service is accessible or
                where your items can be purchased.
              </p>
              <p>
                We reserve the right and sole discretion to determine whether
                the content on your Development Service or the sale of any
                particular item is illegal or otherwise prohibited.
              </p>
            </li>
            <li>
              <h5>Right to Terminate Services</h5>
              <p>
                You understand and agree that We have the absolute right and
                power, in Our sole discretion and without any liability to You
                whatsoever, to terminate your Service if:
              </p>
              <ol className="termination-list">
                <li className="termination-list-item">
                  <p>
                    You fail to schedule and complete an initial interview for
                    development services within six (6) months of purchase.
                  </p>
                </li>
                <li className="termination-list-item">
                  <p>
                    You fail to provide any other requested content, feedback or
                    approval within thirty (30) days of our request.
                  </p>
                </li>
                <li className="termination-list-item">
                  <p>
                    It has been deemed by Us in our sole discretion that a
                    violation of this Agreement has taken place.
                  </p>
                </li>
              </ol>
            </li>
            <li>
              <h4>Account</h4>
              <p>
                In order to access some of the features of this Site or use some
                of the Services, you will have to create an Account. You
                represent and warrant to Us that all information you submit when
                you create your Account is accurate, current and complete, and
                that you will keep your Account information accurate, current
                and complete.{' '}
              </p>
              <p>
                If We have reason to believe that your Account information is
                untrue, inaccurate, out-of-date or incomplete, We reserve the
                right, in its sole and absolute discretion, to suspend or
                terminate your Account.{' '}
              </p>
            </li>
            <li>
              <h4>Payments</h4>

              <p>
                You agree to pay Us for services according to the following
                terms:
              </p>
              <ol className="payments-list">
                <li className="payments-list-item">
                  <h5>Payment Types</h5>
                  You may pay for Services by using any of the following
                  “Payment Methods”: (i) valid credit card; (ii) electronic
                  payment from your personal or business checking account, as
                  appropriate (and as defined below); or (iv) PayPal.
                </li>
                <li className="payments-list-item">
                  <h5>Due at Time of Order</h5>
                  For websites, web applications, and software development, at
                  the time of order You agree to pay all 75% of amounts due for
                  Services at the time you order them. For domain services, You
                  agree to pay 100% of the price of Services at the time of
                  order.
                </li>
                <li className="payments-list-item">
                  <h5>Balance of Payment</h5>
                  For websites, web applications, and software development, 25%
                  of the cost of services will be due when the You are presented
                  with a website or web application that meets all agreed upon
                  functional requirements. For websites and web applications,
                  unless agreed upon in writing, functional requirements will be
                  only those expressly listed in the services catalog on this
                  Site. Functional requirements for software development will be
                  agreed upon in writing prior to time of order.
                </li>

                <li className="payments-list-item">
                  <h5>Non-Refundable</h5>
                  All amounts paid at time of order are non-refundable.
                </li>
              </ol>
            </li>
            <li>
              <h4>Availability of Site and Services</h4>
              <p>
                Subject to the terms and conditions of this Agreement and our
                other policies and procedures, We shall use commercially
                reasonable efforts to attempt to provide this Site and the
                Services on a twenty-four (24) hours a day, seven (7) days a
                week basis.{' '}
              </p>
              <p>
                You acknowledge and agree that from time to time this Site may
                be inaccessible or inoperable for any reason including, but not
                limited to, equipment malfunctions; periodic maintenance,
                repairs or replacements that we undertake from time to time; or
                causes beyond our reasonable control or that are not reasonably
                foreseeable including, but not limited to, interruption or
                failure of telecommunication or digital transmission as, hostile
                network attacks, network congestion or other failures.{' '}
              </p>
              <p>
                You acknowledge and agree that we have no control over the
                availability of this Site or the Service on a continuous or
                uninterrupted basis, and that we assume no liability to you or
                any other party with regard thereto.
              </p>
            </li>

            <li>
              <h4>as to Third-Party Websites</h4>
              <p>
                This Site and the Services found at this Site may contain as to
                third-party websites that are not owned or controlled by Us. We
                assume no responsibility for the content, terms and conditions,
                privacy policies, or practices of any third-party websites. In
                addition, We do not censor or edit the content of any
                third-party websites.{' '}
              </p>
              <p>
                By using this Site or the Services found at this Site, you
                expressly release Us from any and all liability arising from
                your use of any third-party website. Accordingly, We encourage
                You to be aware when You leave this Site or the Services found
                at this Site and to review the terms and conditions, privacy
                policies, and other governing documents of each other website
                that you may visit.
              </p>
            </li>
            <li>
              <h4>No Warranty</h4>

              <p>
                The Services offered through this Site are provided “as is” and
                on an “as available” basis. We disclaim all warranties of any
                kind, whether express or implied, relating to the Websites, Web
                Applications, Software Development and Domain Services including
                all content delivered in connection thereto, including but not
                limited to:
              </p>
              <ol className="warranty-list">
                <li>
                  any implied warranty of merchantability, fitness for a
                  particular purpose, title, quiet enjoyment, or
                  non-infringement;
                </li>
                <li>
                  any warranty arising out of course of dealing, usage, or
                  trade; or any warranty or guaranty relating to availability,
                  accuracy, error rate, system integrity, or uninterrupted
                  access. We do not warrant that:
                  <ol className="warranty-no-no-list">
                    <li>
                      the Websites, Web Applications, Software Developed or
                      Domain Services will be secure or available at any
                      particular time or location;
                    </li>
                    <li>
                      any defects or errors as may arise after deployment will
                      be corrected;
                    </li>
                    <li>
                      any content or software available at this Site or in any
                      delivered Service is now, or will be in the future, free
                      of viruses or other harmful components;
                    </li>
                    <li>
                      the results of using the deployed Websites, Web
                      Applications, or Software Developed will meet Your ongoing
                      requirements.
                    </li>
                  </ol>
                </li>
              </ol>
              <p>
                Your use of our Websites, Web Applications, Software Development
                and Domain Services is solely at your own risk.
              </p>
              <p>
                We are not responsible for the content that you access through
                Your use of the Websites, Web Applications, or Software
                Developed by Us, or content of other users of those Services. To
                the extent permitted by applicable law, you release us from all
                liability relating to such content.
              </p>
            </li>
            <li>
              <h4>Limitation of Liability</h4>
              <p>
                To the fullest extent permitted by law, in no event will we be
                liable to you for any indirect, incidental, special,
                consequential or punitive damages (including damages for loss of
                profits, goodwill, or any other intangible loss) arising out of
                or relating to your access to or use of Services covered under
                this Agreement, whether based on warranty, contract, tort
                (including negligence), statute, or any other legal theory, and
                whether or not we have been informed of the possibility of
                damage.
              </p>
              <p>
                To the fullest extent permitted by law, our aggregate liability
                to you for all claims arising out of or relating to Services
                provided under this Agreement, whether in contract, tort, or
                otherwise, shall not exceed the lesser of: (a) $2,500 or (b) the
                aggregate amounts You have paid Us during the six-month period
                preceding the date of the claim.
              </p>
            </li>
            <li>
              <h4>Indemnification</h4>
              <p>
                You agree to protect, defend, indemnify and hold Us, Our
                officers, directors, employees, agents, and third party service
                providers from and against any and all claims, demands, costs,
                expenses, losses, liabilities and damages of every kind and
                nature (including, without limitation, reasonable attorneys’
                fees) imposed upon or incurred by Us directly or indirectly
                arising from:
              </p>
              <ol className="indemnify-list">
                <li>
                  your use of and access to Services covered under this
                  Agreement;
                </li>
                <li>
                  your violation of any provision of this Agreement or the
                  policies or agreements which are incorporated herein; and/or
                </li>
                <li>
                  your violation of any third-party right, including without
                  limitation any intellectual property or other proprietary
                  right.
                </li>
              </ol>
              <p>
                The indemnification obligations under this section shall survive
                any termination or expiration of this Agreement or Your use of
                this Site or the Services found at this Site.
              </p>
            </li>
            <li>
              <h4>Miscellaneous</h4>

              <ol id="misc-list">
                <li className="misc-list-item">
                  <h5>Governing Law</h5>
                  <p>
                    This Agreement and any dispute related thereto is governed
                    by the laws of the State of Iowa without regard to conflict
                    of law principles. You and Us submit to the personal and
                    exclusive jurisdiction of the state courts and federal
                    courts located within Linn County, Iowa for resolution of
                    any lawsuit or court proceeding permitted under this
                    Agreement.
                  </p>
                </li>
                <li className="misc-list-item">
                  <h5>Compliance With Local Laws</h5>
                  <p>
                    We make no representation or warranty that the content
                    available on this Site or the Services found at this Site
                    are appropriate in every country or jurisdiction, and access
                    to this Site or the Services found at this Site from
                    countries or jurisdictions where its content is illegal is
                    prohibited. Users who choose to access this Site or the
                    Services found at this Site are responsible for compliance
                    with all local laws, rules and regulations.
                  </p>
                </li>

                <li className="misc-list-item">
                  <h5>Force Majeure</h5>

                  <p>
                    We shall not be liable for nonperformance of the terms
                    herein to the extent that We are prevented from performing
                    as a result of any act or event which occurs and is beyond
                    Our reasonable control, including, without limitation, acts
                    of God, war, unrest or riot, strikes, any action of a
                    governmental entity, weather, quarantine, fire, flood,
                    earthquake, explosion, utility or telecommunications
                    outages, Internet disturbance, epidemic, pandemic or any
                    unforeseen change in circumstances, or any other causes
                    beyond our reasonable control.
                  </p>
                </li>
                <li className="misc-list-item">
                  <h5>Relationship of Parties</h5>
                  <p>
                    The parties are independent contractors. This Agreement does
                    not create a partnership, franchise, joint venture, agency,
                    fiduciary or employment relationship between the parties.
                    Neither party, nor any of their respective affiliates, is an
                    agent of the other for any purpose or has the authority to
                    bind the other.
                  </p>
                </li>

                <li className="misc-list-item">
                  <h5>Successors and Assigns</h5>
                  <p>
                    This Agreement shall be binding upon and inure to the
                    benefit of the parties hereto and their respective heirs,
                    successors and assigns.
                  </p>
                </li>
                <li className="misc-list-item">
                  <h5>No Third-Party Beneficiaries</h5>
                  <p>
                    Nothing in this Agreement shall be deemed to confer any
                    third-party rights or benefits.
                  </p>
                </li>
                <li className="misc-list-item">
                  <h5>Titles and Headings</h5>
                  <p>
                    The titles and headings of this Agreement are for
                    convenience and ease of reference only and shall not be
                    utilized in any way to construe or interpret the agreement
                    of the parties as otherwise set forth herein.{' '}
                  </p>
                </li>
                <li className="misc-list-item">
                  <h5>Independent Severable Covenants</h5>
                  <p>
                    Each covenant and agreement in this Agreement shall be
                    construed for all purposes to be a separate and independent
                    covenant or agreement. If a court of competent jurisdiction
                    holds any provision (or portion of a provision) of this
                    Agreement to be illegal, invalid, or otherwise
                    unenforceable, the remaining provisions (or portions of
                    provisions) of this Agreement shall not be affected thereby
                    and shall be found to be valid and enforceable to the
                    fullest extent permitted by law.
                  </p>
                </li>
              </ol>
            </li>

            <li>
              <h4>Contact Information</h4>
              <p>
                Martin Dwyer
                <br />
                MD Web Technologies
                <br />
                168 16th Ave SW
                <br />
                Cedar Rapids, Iowa 52404
              </p>
            </li>
          </ol>
        </div>
      </div>
    </div>
  );
};

export default Terms;
