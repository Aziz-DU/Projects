function(doc) {
   if ( doc.random && doc.band) {
     emit (doc.random, doc.band);
   }
}
   
