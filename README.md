# DevoirsDAMP

1. Build the examples from the course.
2. Look through the android.provider package and choose 5 providers and explore their date.
Explain a few applications idea that you can build based on that (readme.txt with this
explanation or a word document)
<p>
1)I created 
CalendarContract:
You can built an aplication that can add events reminders.
It can create a new calendar.
It can add, query, update and delete events.
It cand add reminders to the event.</p>
<p>
2)ContactsContract:
 You can create an application that will diplay all contcts in a listview retrived from ContactsContract.Contacts table.
 You can click on it and it will display contact details suc as : name(DISPLAY_NAME in ContactsContract.Data), phone number, email(by quering the cursor and dispalyng data found in column ContactsContract.CommonDataKinds.Phone and CommonDataKinds.Email.DATA) 
oreder it by most contacted or last contacted int (	TIMES_CONTACTED 	,long 	LAST_TIME_CONTACTED )	.
You can insert contacts by adding information in ContactsContract.RawContacts and ContactsContract.CommonDataKinds.
You can delete a contact by selecting the RawContacts.CONTACT_ID from RawContacts.CONTENT_URI. .
You can update contacts.
You can send messages.
</p>
<p>
3)UserDictionary:
You can create an app that manages userdictionar: adds,deletes, searches a word or displays words.
</p>
<p>
4)VoicemailContract:
An app that can display all the voice mails in a list and play the. we can display information like Calls.NUMBER,Calls.DATE,Calls.DURATION and that can delete it.
5) It can be created an appilication that displays the call history with details:name, phoe number, date,duration.
It could also display a popup window when user receives a call.
</p>



 
3. Build one of those suggestions.
https://github.com/NGabi/DevoirsDAMP
